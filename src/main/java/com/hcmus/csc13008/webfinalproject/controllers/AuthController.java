package com.hcmus.csc13008.webfinalproject.controllers;

import com.hcmus.csc13008.webfinalproject.models.User;
import com.hcmus.csc13008.webfinalproject.services.AuthService;
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
        modelAndView.addObject("login", new User());

        if (error != null) {
            modelAndView.addObject("error", "Đăng nhập không thành công!");
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView indexRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/register");
        modelAndView.addObject("register", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@ModelAttribute("register") User register) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/register");
        modelAndView.addObject("register", new User());
        if (authService.checkRegister(register)) {
            modelAndView.setViewName("redirect:/auth/login");
        }
        return modelAndView;
    }


}
