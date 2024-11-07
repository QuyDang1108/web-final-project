package com.hcmus.csdlnc.sushirestaurant.services.impl;

import com.hcmus.csdlnc.sushirestaurant.models.Account;
import com.hcmus.csdlnc.sushirestaurant.repositories.AccountRepository;
import com.hcmus.csdlnc.sushirestaurant.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean checkRegister(Account account) {
        if (!accountRepository.existsByUsername(account.getUsername())) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            return true;
        }

        return false;
    }
}
