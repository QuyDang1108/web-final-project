package com.hcmus.csc13008.webfinalproject.repositories;

import com.hcmus.csc13008.webfinalproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);
}
