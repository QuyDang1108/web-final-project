package com.hcmus.csdlnc.sushirestaurant.repositories;

import com.hcmus.csdlnc.sushirestaurant.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByUsername(String username);

    Account findByUsername(String username);
}
