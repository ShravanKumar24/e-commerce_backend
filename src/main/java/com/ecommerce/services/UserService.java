package com.ecommerce.services;

import com.ecommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;


    public UserDetailsService userDetailsService() {
        // Implementing the unimplemented method in UserDetailsService Interface and
        // returning it

        return new UserDetailsService() {
            // Implementing the loadUserByUsername method also throws
            // UsernameNotFoundException when user not found
            // in our case userName is email;
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                // Returning the details from DB using UserRepo
                return userRepo.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
            }

        };
    }
}
