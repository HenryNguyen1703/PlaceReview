package vn.ngochieu.com.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.ngochieu.com.entity.Users;
import vn.ngochieu.com.exception.LogicCustomException;
import vn.ngochieu.com.payload.request.UserSignUpRequest;
import vn.ngochieu.com.payload.response.UserLogInResponse;
import vn.ngochieu.com.repository.UserRepository;
import vn.ngochieu.com.service.UserService;
import vn.ngochieu.com.util.JwtUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> signup(UserSignUpRequest userSignUpRequest, HttpServletRequest request) {
        Optional<Users> checker = userRepository.findByEmail(userSignUpRequest.getEmail());
        if (checker.isPresent()) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Email is already in use");
            logicCustomException.setCode(400);
            throw logicCustomException;
        }
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
