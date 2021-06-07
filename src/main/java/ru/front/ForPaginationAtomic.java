package ru.front;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.atomic.AtomicInteger;

//Класс-конфигурация для нормальной пагинации страниц с использованием AtomicInt'а
//Не факт, что сработает, долго думал
//теоретически создан для многопоточности:чтобы при переключении страницы одним пользователем,
//не менялась страница у остальных пользователей
@Configuration
public class ForPaginationAtomic {
    @Bean
    @Scope(value = "prototype")
    public AtomicInteger atomicInteger() {
        return new AtomicInteger(0);
    }//при вызове первое значение будет нулем - первая страница новостей
}
