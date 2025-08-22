package vn.ngochieu.com.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.ngochieu.com.payload.request.UserSignUpRequest;

public interface UserService extends UserDetailsService {

    ResponseEntity<?> signup(UserSignUpRequest userSignUpRequest, HttpServletRequest request);
}
