package com.hcmus.csdlnc.sushirestaurant.config.security;

import com.hcmus.csdlnc.sushirestaurant.enums.EAcountStatus;
import com.hcmus.csdlnc.sushirestaurant.models.Account;
import com.hcmus.csdlnc.sushirestaurant.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        boolean isDisable = account.getStatus() != EAcountStatus.ACTIVE;

        if (isDisable) {
            try {
                throw new Exception("Tài khoản của bạn đã bị khóa!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return org.springframework.security.core.userdetails.User.withUsername(account.getUsername())
            .password(account.getPassword())
            .roles(String.valueOf(account.getType()))
            .disabled(isDisable)
            .build();
    }
}
