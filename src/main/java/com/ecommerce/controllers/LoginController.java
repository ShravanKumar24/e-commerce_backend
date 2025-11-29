package com.ecommerce.controllers;

import com.ecommerce.dtos.AuthenticationResponse;
import com.ecommerce.dtos.SignInDto;
import com.ecommerce.dtos.SignupDto;
import com.ecommerce.services.interfaces.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController("/api/")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> userRegister(@RequestBody SignupDto signupDto) throws Exception {
        return new ResponseEntity<>(loginService.userRegister(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@RequestBody SignInDto signInDto) throws Exception {
        return new ResponseEntity<>(loginService.userLogin(signInDto), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.getRefreshToken(request, response);
    }

}


