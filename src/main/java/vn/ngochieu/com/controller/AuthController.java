package vn.ngochieu.com.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ngochieu.com.payload.request.UserSignUpRequest;
import vn.ngochieu.com.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Authentication APIs", description = "APIs for signup and login")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "Sign up", description = "API for registered users")
    public ResponseEntity<?> signup(@Validated @RequestBody UserSignUpRequest userSignUpRequest, HttpServletRequest request) {
        return userService.signup(userSignUpRequest, request);
    }
}
