package com.ecommerce.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dtos.AuthenticationResponse;
import com.ecommerce.dtos.SignInDto;
import com.ecommerce.dtos.SignupDto;
import com.ecommerce.entites.Address;
import com.ecommerce.entites.Role;
import com.ecommerce.entites.Token;
import com.ecommerce.entites.TokenType;
import com.ecommerce.entites.User;
import com.ecommerce.repositories.TokenRepo;
import com.ecommerce.repositories.UserRepo;
import com.ecommerce.services.interfaces.LoginService;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
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
			if (!signupDto.getAddress().isEmpty() ) {
				List<Address> addresses = signupDto.getAddress().stream().map(address -> {
					address.setUser(user); // Set owning user in address
					return address;
				}).collect(Collectors.toList());
				user.setAddresses(addresses);
			}

			userRepo.save(user);
			var jwtToken = jwtService.generateToken(user);
			var refreshToken = jwtService.generateRefreshToken(user);
			saveUserToken(user, jwtToken);
			return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
		}
	}

	@Override
	public AuthenticationResponse userLogin(SignInDto signInDto) throws Exception {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));

		var user = userRepo.findByEmail(signInDto.getEmail())
				.orElseThrow(() -> new Exception("Invalid email or password"));
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();

	}


	private void saveUserToken(User user, String jwtToken) {
		Token token = new Token();
		token.setToken(jwtToken);
		token.setUser(user);
		token.setTokenType(TokenType.BEARER);
		token.setLoggedOut(false);
		tokenRepo.save(token);
	}
	
	 private void revokeAllUserTokens(User user) {
		    var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
		    if (validUserTokens.isEmpty()) {
		      return;
		    }
		    validUserTokens.forEach(token -> {
		      token.setLoggedOut(true);
		    });
		    tokenRepo.saveAll(validUserTokens);
		  }
	 
	 
		@Override
		public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception, DatabindException, IOException {

		final String authHeader = request.getHeader("Authorization");
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      var user = userRepo.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        var authResponse = AuthenticationResponse.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }

		}
}


