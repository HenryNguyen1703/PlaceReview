package vn.ngochieu.com.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.ngochieu.com.entity.Users;
import vn.ngochieu.com.exception.LogicCustomException;
import vn.ngochieu.com.payload.request.UserLogInRequest;
import vn.ngochieu.com.payload.request.UserSignUpRequest;
import vn.ngochieu.com.payload.response.UserLogInResponse;
import vn.ngochieu.com.repository.UserRepository;
import vn.ngochieu.com.service.UserService;
import vn.ngochieu.com.util.JwtUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> signup(UserSignUpRequest userSignUpRequest, HttpServletRequest request) {
        Optional<Users> checker = userRepository.findByEmail(userSignUpRequest.getEmail());
        if (checker.isPresent()) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Email is already in use");
            logicCustomException.setCode(400);
            throw logicCustomException;
        }

        // Create new user
        Users newUser = new Users();
        newUser.setEmail(userSignUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        newUser.setUsername(userSignUpRequest.getUsername());
        newUser.setRole(userSignUpRequest.getRole());
        Users savedUser = userRepository.save(newUser);

        //Generate Jwt token
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setUsername(savedUser.getUsername());
        userLogInResponse.setEmail(savedUser.getEmail());
        userLogInResponse.setToken(JwtUtils.createToken(savedUser, request));
        return ResponseEntity.ok(userLogInResponse);
    }

    @Override
    public ResponseEntity<?> login(UserLogInRequest userLogInRequest, HttpServletRequest request) {
        Optional<Users> checker = userRepository.findByEmail(userLogInRequest.getEmail());
        if(checker.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        if(!passwordEncoder.matches(userLogInRequest.getPassword(), checker.get().getPassword())) {
            return ResponseEntity.status(401).build();
        }

        // Generate token
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setUsername(checker.get().getUsername());
        userLogInResponse.setEmail(checker.get().getEmail());
        userLogInResponse.setToken(JwtUtils.createToken(checker.get(), request));
        return ResponseEntity.ok(userLogInResponse);
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
