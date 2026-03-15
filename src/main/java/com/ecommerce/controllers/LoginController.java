package com.ecommerce.controllers;

import com.ecommerce.dtos.AuthenticationResponse;
import com.ecommerce.dtos.SignInDto;
import com.ecommerce.dtos.SignupDto;
import com.ecommerce.services.interfaces.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> userRegister(@Valid @RequestBody SignupDto signupDto) throws Exception {
        return new ResponseEntity<>(loginService.userRegister(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@Valid @RequestBody SignInDto signInDto) throws Exception {
        return new ResponseEntity<>(loginService.userLogin(signInDto), HttpStatus.OK);
    }


    @PostMapping("/refresh-token")
    public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        loginService.getRefreshToken(request, response);
    }

}


