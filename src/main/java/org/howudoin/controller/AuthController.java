package org.howudoin.controller;

import org.howudoin.LoginRequest;
import org.howudoin.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import static org.howudoin.LoginResponse.builder;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
   // private JwtHelper jwtHelper;
    private org.howudoin.JwtHelper JwtHelper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // Ensure correct syntax for method call (add parentheses)
        this.doAuthenticate(loginRequest.getUsername(), loginRequest.getPassword()); // Call with parentheses for password

        // Load user details by username
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        // Generate JWT token using the username from userDetails
        String token = this.JwtHelper.generateToken(userDetails.getUsername());

        // Build the response with the token and username
        LoginResponse response = builder()
                .token(token)
                .userName(userDetails.getUsername())
                .build();

        return ResponseEntity.ok(response); // Return the response with OK status
    }


    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }
}