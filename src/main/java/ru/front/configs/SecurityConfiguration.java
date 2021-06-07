package ru.front.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.front.model.Roles;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()//разрешение войти на эту страницу абсолютно любым
                .antMatchers("/auth/login/**").anonymous()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")//если есть роль админа, то открыты страницы для админов
                //но страниц для админов пока нет
                .and()
                .formLogin()
                .loginPage("/login")//обозначение своей страницы для логинов
                .defaultSuccessUrl("/")//если смог пройти -  это след. страница, типо перехода после логина
                .and()
                .rememberMe()//функция "запомнить меня"
                .and()
                .logout()
                .logoutUrl("/logout")//своя стпраница для выхода с акка
                //все с логином
                .clearAuthentication(true)//убирание аутентификации
                .invalidateHttpSession(true)//сделать сессию HTTP
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/");//-страница после выхода
    }
    //не могу найти что не так с конфигом!

    
    //для пароля защита
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                //Первичные пользователей для теста:
                //Один с ролью Юзера обычного, другой с Админкой
                //не забыть, что могут еще быть и те, кто еще не прошел логин - неизвестные
                //тестовый АДМИН
                User.builder()
                        .username("Admin")//пароль
                        .password(passwordEncoder().encode("Admin"))//login
                        .authorities(Roles.ADMIN.getAuthorities())
                        .build(),
                //тестовый ЮЗЕР
                User.builder()
                        .username("User")//логин
                        .password(passwordEncoder().encode("User"))//пароль
                        .authorities(Roles.USER.getAuthorities())
                        .build()

        );
    }
}

