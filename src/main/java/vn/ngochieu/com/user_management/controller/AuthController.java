package vn.ngochieu.com.user_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ngochieu.com.payload.response.ApiResponse;
import vn.ngochieu.com.user_management.payload.request.UserLogInRequest;
import vn.ngochieu.com.user_management.payload.request.UserSignUpRequest;
import vn.ngochieu.com.user_management.payload.response.UserLogInResponse;
import vn.ngochieu.com.user_management.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Authentication APIs", description = "APIs for signup and login")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
public class AuthController {

    UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "Sign up", description = "API for registered users")
    public ResponseEntity<?> signup(@RequestBody @Valid UserSignUpRequest userSignUpRequest, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        ApiResponse<UserLogInResponse> apiResponse = ApiResponse.<UserLogInResponse>builder()
                .data(userService.signup(userSignUpRequest, request, response))
                .message("Sign up successful")
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/login")
    @Operation(summary = "Log in", description = "API for users login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLogInRequest userLogInRequest, HttpServletRequest request) {
        ApiResponse<UserLogInResponse> response = ApiResponse.<UserLogInResponse>builder()
                .data(userService.login(userLogInRequest, request))
                .message("Log in successful")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("verify-otp")
    @Operation(summary = "Verify Otp", description = "API for verify otp from cookies")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> otpRequest, HttpServletRequest request, HttpServletResponse response) {
        userService.verifyOtp(otpRequest, request, response);
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .message("Verify Otp successful")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
