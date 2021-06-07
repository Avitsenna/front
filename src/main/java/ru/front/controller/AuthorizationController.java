package ru.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//все для авторизации
@Controller
@RequestMapping("/auth")
public class AuthorizationController {
//страница для входа/логина
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
//выхода
    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }


}
