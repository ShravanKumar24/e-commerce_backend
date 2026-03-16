package com.ecommerce.controllers;

import com.ecommerce.dtos.AuthenticationResponse;
import com.ecommerce.dtos.ApiResponse;
import com.ecommerce.dtos.SignInDto;
import com.ecommerce.dtos.SignupDto;
import com.ecommerce.services.interfaces.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> userRegister(@Valid @RequestBody SignupDto signupDto) {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>(
                true,
                "User Registered Successfully",
                loginService.userRegister(signupDto),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> userLogin(@Valid @RequestBody SignInDto signInDto) {

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>(
                true,
                "Login Success",
                loginService.userLogin(signInDto),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>(
                true,
                "Token refreshed Successfully",
                loginService.getRefreshToken(request, response),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

}


