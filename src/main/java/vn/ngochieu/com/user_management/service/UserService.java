package vn.ngochieu.com.user_management.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.ngochieu.com.user_management.payload.request.UserLogInRequest;
import vn.ngochieu.com.user_management.payload.request.UserSignUpRequest;
import vn.ngochieu.com.user_management.payload.response.UserLogInResponse;

import java.util.Map;

public interface UserService extends UserDetailsService {

    UserLogInResponse signup(UserSignUpRequest userSignUpRequest, HttpServletRequest request, HttpServletResponse response) throws MessagingException;

    UserLogInResponse login(UserLogInRequest userLogInRequest, HttpServletRequest request);

    void verifyOtp(Map<String, String> userOtp, HttpServletRequest request, HttpServletResponse response);
}
