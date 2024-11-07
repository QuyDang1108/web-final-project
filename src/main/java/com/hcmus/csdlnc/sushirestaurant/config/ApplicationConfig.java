package com.hcmus.csdlnc.sushirestaurant.config;

import com.hcmus.csdlnc.sushirestaurant.enums.EAcountStatus;
import com.hcmus.csdlnc.sushirestaurant.enums.EType;
import com.hcmus.csdlnc.sushirestaurant.models.Account;
import com.hcmus.csdlnc.sushirestaurant.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {
    private final AccountRepository accountRepository;

    @Value("${application.admin.default.username}")
    private String adminUsername;

    @Value("${application.admin.default.password}")
    private String adminPassword;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "spring",
        value = "datasource.driver-class-name",
        havingValue = "com.microsoft.sqlserver.jdbc.SQLServerDriver")
    ApplicationRunner applicationRunner() {
        log.info("Initializing application.....");
        return args -> {
            if (!accountRepository.existsByUsername(adminUsername)) {
                Account account = Account.builder()
                    .username(adminUsername)
                    .password(passwordEncoder().encode(adminPassword))
                    .status(EAcountStatus.ACTIVE)
                    .type(EType.MANAGER)
                    .build();
                accountRepository.save(account);
                log.warn("Create: admin user has been created: username = {}, password = {} ",
                    adminUsername, adminPassword);
            } else {
                log.warn("Admin user: username = {}, password = {} ", adminUsername, adminPassword);
            }
            log.info("Application initialization completed .....");
        };
    }
}
