package com.binary.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.binary.configuration.JwtService;
import com.binary.entity.Role;
import com.binary.entity.Token;
import com.binary.entity.TokenType;
import com.binary.entity.User;
import com.binary.repository.TokenRepository;
import com.binary.repository.UserRepository;
import com.binary.request.AuthenticationRequest;
import com.binary.request.RegisterRequest;
import com.binary.response.AuthenticationResponse;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private TokenRepository tokenRepository;

	public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
			TokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.tokenRepository = tokenRepository;
	}

	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User();
		Role role = new Role();
		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
	    // Create a new HashSet to store roles
	    Set<Role> roles = new HashSet<>();
	    roles.add(role);

	    // Set the roles to the user
	    user.setRoles(roles);
		
		var saveUser = userRepository.save(user);

		var jwtToken = jwtService.generateToken(user);

		saveUserToken(saveUser, jwtToken);

		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request, Long userId) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

		var jwtToken = jwtService.generateToken(user);

		revokeAllUserTokens(user);

		var getToken = tokenRepository.findByUserId(userId);
		if (getToken != null) {
			getToken.setExpired(false);
			getToken.setRevoked(false);
			getToken.setToken(jwtToken);

			tokenRepository.save(getToken);
		} else {
			saveUserToken(user, jwtToken);
		}

		return new AuthenticationResponse(jwtToken);
	}

	private void revokeAllUserTokens(User user) {
		var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());

		if (validUserToken.isEmpty()) {
			return;
		}

		validUserToken.forEach(t -> {
			t.setExpired(true);
			t.setRevoked(true);
		});

		tokenRepository.saveAll(validUserToken);
	}

	private void saveUserToken(User user, String jwtToken) {
		Token token = new Token();
		token.setUser(user);
		token.setToken(jwtToken);
		token.setTokenType(TokenType.BEARER);
		token.setRevoked(false);
		token.setExpired(false);

		tokenRepository.save(token);
	}
}
