package org.howudoin.service;

import org.howudoin.model.User;  // Import User model
import org.howudoin.repository.UserRepository;  // Use UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;  // Use UserRepository to fetch user details

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // Fetch user from the repository based on username (email in this case)
            User user = userRepository.findByEmail(username);  // Assuming email is used as username
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            // Extract the username and password from the found user
            String userName = user.getEmail();  // Assuming email is the username
            String password = user.getPassword();

            // Return a UserDetails object, which can be used by Spring Security
            return new org.springframework.security.core.userdetails.User(userName, password, new ArrayList<>());
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + e.toString());
        }
    }
}
