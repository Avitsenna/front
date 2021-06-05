package ru.front;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.atomic.AtomicInteger;

//Класс-конфигурация для нормальной пагинации страниц с использованием AtomicInt'а
//Не факт, что сработает, долго думал
@Configuration
public class ForPaginationAtomic {
    @Bean
    @Scope(value = "prototype")
    public AtomicInteger atomicInteger() {
        return new AtomicInteger(0);
    }
}
