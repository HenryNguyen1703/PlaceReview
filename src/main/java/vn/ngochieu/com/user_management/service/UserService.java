package vn.ngochieu.com.user_management.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.ngochieu.com.user_management.payload.request.UserLogInRequest;
import vn.ngochieu.com.user_management.payload.request.UserSignUpRequest;
import vn.ngochieu.com.user_management.payload.response.UserLogInResponse;

public interface UserService extends UserDetailsService {

    UserLogInResponse signup(UserSignUpRequest userSignUpRequest, HttpServletRequest request);

    UserLogInResponse login(UserLogInRequest userLogInRequest, HttpServletRequest request);
}
