package com.ecommerce.services.interfaces;

import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.dtos.AuthenticationResponse;
import com.ecommerce.dtos.SignInDto;
import com.ecommerce.dtos.SignupDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {

    AuthenticationResponse userRegister(@RequestBody SignupDto signupDto);
    AuthenticationResponse  userLogin(@RequestBody SignInDto signInDto) throws Exception;
    void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
