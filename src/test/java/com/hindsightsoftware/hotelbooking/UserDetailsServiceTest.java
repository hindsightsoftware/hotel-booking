package com.hindsightsoftware.hotelbooking;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceTest {
    private UserDetailsServiceImpl userDetailsService;

    public UserDetailsServiceTest() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDetailsService = new UserDetailsServiceImpl(bCryptPasswordEncoder);
    }

    @Test
    public void defaultUserShouldExist() {
        Assertions.assertDoesNotThrow(() -> userDetailsService.loadUserByUsername("admin"));
    }

    @Test
    public void invalidUserShouldThrow() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("invalid"));
    }
}
