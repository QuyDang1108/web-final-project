package com.hcmus.csdlnc.sushirestaurant.services;

import com.hcmus.csdlnc.sushirestaurant.models.Account;

public interface AuthService {
    boolean checkRegister(Account account);
}
