package com.hcmus.csdlnc.sushirestaurant.controllers;

import com.hcmus.csdlnc.sushirestaurant.models.Account;
import com.hcmus.csdlnc.sushirestaurant.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public ModelAndView indexLogin(@RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        modelAndView.addObject("login", new Account());

        if (error != null) {
            modelAndView.addObject("error", "Đăng nhập không thành công!");
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView indexRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/register");
        modelAndView.addObject("register", new Account());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@ModelAttribute("register") Account register) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/register");
        modelAndView.addObject("register", new Account());
        if (authService.checkRegister(register)) {
            modelAndView.setViewName("redirect:/auth/login");
        }
        return modelAndView;
    }


}
