package org.howudoin;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private JwtHelperUtils jwtHelperUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        // JWT authentication filter logic
        /*String username = request.ge;
        UserDetails user = userDetailsService.loadUserByUsername(username);
        user.getPassword();

        filterChain.doFilter(request,response);*/

            // Step 1: Extract the JWT token from the Authorization header
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);  // Continue if no token is present
                return;
            }

            String token = header.substring(7);  // Extract the token (after "Bearer ")

            try {
                // Step 2: Validate the JWT token (you might use a utility method or a service here)
                String username = jwtHelperUtils.getUsernameFromToken(token);  // Assuming you have a utility to extract the username from the token

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Step 3: Load user details
                    UserDetails user = userDetailsService.loadUserByUsername(username);

                    // Step 4: Validate the token with the user information
                    if (jwtHelperUtils.validateToken(token, user)) {  // Assuming validateToken checks for expiration and authenticity
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                        // Step 5: Set the authentication object in the SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                    else {
                        // Step 6: If token is invalid, reject the request or generate a new token if necessary
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Unauthorized
                        response.getWriter().write("Invalid or expired token");
                        String newToken = jwtHelperUtils.generateToken(user);
                         response.setHeader("Authorization", "Bearer " + newToken);
                }}
            } catch (Exception e) {
                // Handle token parsing or validation errors here (logging, etc.)
                logger.error("Authentication failed: ", e);
            }

            // Step 6: Continue the filter chain
            filterChain.doFilter(request, response);
        }

    }
