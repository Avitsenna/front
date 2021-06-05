package ru.front.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//специальная страница управления для Админов - пока нет функцоинала у страницы
@RestController
public class AdminController {
    //страница для админа
    @GetMapping("/admin")
    public String getAdminPage() {
        return "Administrator page";
    }
}
