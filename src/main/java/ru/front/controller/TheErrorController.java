package ru.front.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

//просто контроллер для вывода ошибок, ничего сложного
public class TheErrorController implements ErrorController {

    //здесь по идее должен быть @Override,но почему-то показывает ошибку при нем
    //такое чувство, как будто проблема или в винде, или во моем коде...причем второй вариант более вероятен
    public String getErrorPath() {
        return "/error";
    }

    //маппинг и получение ошибки
    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

}