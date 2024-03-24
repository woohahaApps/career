package com.woohahaapps.career.controller.view;

import com.woohahaapps.career.dto.SignInForm;
import com.woohahaapps.career.dto.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthViewController {

    @GetMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("signInForm", new SignInForm());
        return "auth/signin";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "auth/signup";
    }
}
