package ru.front.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import ru.front.model.Content;
import ru.front.model.ContentList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class MainController {
    //порт должен отличаться от порта у второй части - но главный порт - 8080
    private final String url = "http://localhost:8090/news";// - для бэка
    private final AtomicInteger atomicInteger;

    public MainController(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    @GetMapping("/")
    public String getMainPage(@RequestParam(value = "page", required = false, defaultValue = "0") String page, ModelMap modelMap) {

        atomicInteger.set(Integer.parseInt(page));//AtomicInteger создан для того, чтобы если проект станет больших
        // масштабов и при изменении страницы одним пользоавтелем , не менялась страница для других(многопоточность)
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url)
                .append("?page=")//для составления номеров страниц
                .append(page);
        String url = stringBuilder.toString();
        WebClient webClient = WebClient.create();
        ContentList responseJson = webClient.get()
                .uri(url)
                .exchange()
                .block()
                .bodyToMono(ContentList.class)
                .block();
        //сам принцип отображения страниц - в виде списка??
        List<Content> contentList = responseJson.getList();
        modelMap.addAttribute("newsList", contentList);

        return "mainpage";
    }

    //для работы с кнопкой для страниц новостей
    //страница НЕ МОЖЕТ БЫТЬ МЕНЬШЕ 0 - сделать этот нюанс
    //еле-еле смог додуматься до логики, с маппингом тоже очень долго возился
    //для нумерации страниц используется параметр URL'а - пагинация -> ?page=
    //ооооооооочень долго думал - самое сложное
    //нет, не самое сложное, верстка и бэк , вот там реал тяжело, не могу найти причины ошибок
    @PostMapping("/button")
    public String page(@ModelAttribute(name = "next") String nextButton,
                       @ModelAttribute(name = "previous") String previousButton) {
        //для след страницы
        if (nextButton.equals("next")) {
            atomicInteger.incrementAndGet();
            return "redirect:/?page=" + atomicInteger.get();// для увеличения номера страницы на 1 и ее получении
        } else {
            atomicInteger.decrementAndGet();//для уменьшения номера страницы и получения ее =
            return "redirect:/?page=" + atomicInteger.get();
        }
    }

}
