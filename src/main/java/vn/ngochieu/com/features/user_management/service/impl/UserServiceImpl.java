package vn.ngochieu.com.features.user_management.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.ngochieu.com.features.user_management.entity.Users;
import vn.ngochieu.com.common.exceptions.LogicCustomException;
import vn.ngochieu.com.features.user_management.mapper.UserMapper;
import vn.ngochieu.com.features.user_management.payload.request.UserLogInRequest;
import vn.ngochieu.com.features.user_management.payload.request.UserSignUpRequest;
import vn.ngochieu.com.features.user_management.payload.response.UserLogInResponse;
import vn.ngochieu.com.features.user_management.repository.UserRepository;
import vn.ngochieu.com.features.user_management.service.UserService;
import vn.ngochieu.com.util.JwtUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public UserLogInResponse signup(UserSignUpRequest userSignUpRequest, HttpServletRequest request) {
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

        //Generate Jwt token
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setUsername(savedUser.getUsername());
        userLogInResponse.setEmail(savedUser.getEmail());
        userLogInResponse.setToken(JwtUtils.createToken(savedUser, request));
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
