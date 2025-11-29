package com.ecommerce.services;

import com.ecommerce.dtos.AuthenticationResponse;
import com.ecommerce.dtos.SignInDto;
import com.ecommerce.dtos.SignupDto;
import com.ecommerce.entites.*;
import com.ecommerce.repositories.TokenRepo;
import com.ecommerce.repositories.UserRepo;
import com.ecommerce.services.interfaces.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class LoginServiceimpl implements LoginService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepo tokenRepo;


    @Override
    public AuthenticationResponse userRegister(SignupDto signupDto) {
        if (userRepo.findByEmail(signupDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email already exists");
        } else {
            User user = new User();
            user.setFirstName(signupDto.getFirstName());
            user.setLastName(signupDto.getLastName());
            user.setEmail(signupDto.getEmail());
            user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
            user.setRole(Role.USER);
            user.setMobileNumber(signupDto.getMobileNumber());
            user.setActiveStatus(true);

            // Map addresses from DTO and associate with user
            if (signupDto.getAddress() != null) {
                List<Address> addresses = signupDto.getAddress().stream().map(address -> {
                    address.setUser(user); // Set owning user in address
                    return address;
                }).collect(Collectors.toList());
                user.setAddress(addresses);
            }

            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
        }
    }

    @Override
    public AuthenticationResponse userLogin(SignInDto signInDto) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(),signInDto.getPassword()));
    var user=

        return null;
    }

    @Override
    public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) {

    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setUser(user);
        token.setTokeType(TokenType.BEARER);
        token.setLoggedOut(false);
        tokenRepo.save(token);
    }
}
