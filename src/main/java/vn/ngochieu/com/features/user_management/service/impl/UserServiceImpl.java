package vn.ngochieu.com.features.user_management.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.ngochieu.com.common.exceptions.LogicCustomException;
import vn.ngochieu.com.features.user_management.entity.Users;
import vn.ngochieu.com.features.user_management.mapper.UserMapper;
import vn.ngochieu.com.features.user_management.payload.request.UserLogInRequest;
import vn.ngochieu.com.features.user_management.payload.request.UserSignUpRequest;
import vn.ngochieu.com.features.user_management.payload.response.UserLogInResponse;
import vn.ngochieu.com.features.user_management.repository.UserRepository;
import vn.ngochieu.com.features.user_management.service.UserService;
import vn.ngochieu.com.service.EmailService;
import vn.ngochieu.com.service.OtpService;

import vn.ngochieu.com.util.JwtUtils;
import vn.ngochieu.com.util.OtpUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    OtpService otpService;

    EmailService emailService;

    @Override
    public UserLogInResponse signup(UserSignUpRequest userSignUpRequest, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        Optional<Users> checker = userRepository.findByEmail(userSignUpRequest.getEmail());
        if (checker.isPresent()) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Email is already in use");
            logicCustomException.setCode(400);
            throw logicCustomException;
        }

        // Create new user
        Users newUser = userMapper.toEntity(userSignUpRequest);
        newUser.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        Users savedUser = userRepository.save(newUser);

        // Send otp to Email
        String otp = OtpUtils.generateOtp(6);
        otpService.saveOtp(savedUser.getEmail(), otp);
        emailService.sendOtpEmail(savedUser.getEmail(), savedUser.getUsername(), otp, 5);

        //Generate Jwt token
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setUsername(savedUser.getUsername());
        userLogInResponse.setEmail(savedUser.getEmail());
        userLogInResponse.setToken(JwtUtils.createToken(savedUser, request));

        // Save to cookie
        ResponseCookie responseCookie = ResponseCookie.from("jwt_token", userLogInResponse.getToken())
                .httpOnly(true)
                .secure(false) // true if deploy for HTTPS
                .path("/")
                .maxAge(Duration.ofMinutes(5))
                .sameSite("Strict")
                .build();
        // Save cookie to header
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return userLogInResponse;
    }



    @Override
    public UserLogInResponse login(UserLogInRequest userLogInRequest, HttpServletRequest request) {
        Optional<Users> checker = userRepository.findByEmail(userLogInRequest.getEmail());
        if(checker.isEmpty()) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Username or password is incorrect");
            logicCustomException.setCode(401);
            throw logicCustomException;
        }
        if(!passwordEncoder.matches(userLogInRequest.getPassword(), checker.get().getPassword())) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Username or password is incorrect");
            logicCustomException.setCode(401);
            throw logicCustomException;
        }

        // Generate token
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setUsername(checker.get().getUsername());
        userLogInResponse.setEmail(checker.get().getEmail());
        userLogInResponse.setToken(JwtUtils.createToken(checker.get(), request));
        return userLogInResponse;
    }



    @Override
    public void verifyOtp(Map<String, String> userOtp, HttpServletRequest request, HttpServletResponse response) {
        String otp = userOtp.get("otp");

        // Take token from cookies
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt_token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Cannot find token in cookie");
            logicCustomException.setCode(400);
            throw logicCustomException;
        }

        // Take email from token
        String email = JwtUtils.extractUsername(token);

        // Check OTP in redis
        String redisOtp = otpService.getOtp(email);
        if(redisOtp == null) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("OTP doesn't exist or is expired");
            logicCustomException.setCode(400);
            throw logicCustomException;
        }
        if (!redisOtp.equals(otp)) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Otp is incorrect");
            logicCustomException.setCode(400);
            throw logicCustomException;
        }

        // Update status
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(Users.Status.ACTIVE);
        userRepository.save(user);

        // Delete otp
        otpService.deleteOtp(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> checker = userRepository.findByEmail(username);
        if (checker.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        Users user = checker.get();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Users.Role role = user.getRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        grantedAuthorities.add(simpleGrantedAuthority);
        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
