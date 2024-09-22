package com.hcmus.csc13008.webfinalproject.services.impl;

import com.hcmus.csc13008.webfinalproject.models.User;
import com.hcmus.csc13008.webfinalproject.repositories.UserRepository;
import com.hcmus.csc13008.webfinalproject.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean checkRegister(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }

        return false;
    }
}
