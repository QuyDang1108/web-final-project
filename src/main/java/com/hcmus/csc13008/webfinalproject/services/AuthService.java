package com.hcmus.csc13008.webfinalproject.services;

import com.hcmus.csc13008.webfinalproject.models.User;

public interface AuthService {
    boolean checkRegister(User user);
}
