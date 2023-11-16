package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler,
                             UserDetailsService userDetailsService) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.cors().and().
//http
        // .csrf().disable().//безопасность
        //  .authorizeRequests()
        // .antMatchers("/", "/index").permitAll()//antMatchers указ url-адреса "/" и "/index" разрешены всем
        // .antMatchers("/admin/**").hasRole("ADMIN")//В "/admin/**" могут заходить только юзеры с ролью "ADMIN"
        //.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")//все
        //.and().formLogin().successHandler(successUserHandler).permitAll()
        // .and()
        // .logout().logoutSuccessUrl("/login");
///////
        http.authorizeRequests()
                .antMatchers("/").permitAll() // Разрешаем доступ к главной странице всем
                .antMatchers("/admin/**", "/adminApi/**").hasRole("ADMIN")
                .antMatchers("/user/**", "/userApi/**", "/adminApi/**").hasAnyRole("USER", "ADMIN")
                //.anyRequest().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successUserHandler)
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .cors().and().csrf().disable();
    }
    ///////
        /*http.authorizeRequests()
                .antMatchers("/", "/static/**", "/js/**", "/css/**").permitAll()
                .antMatchers("/").permitAll()
                //.antMatchers("/user").hasAnyRole("USER", "ADMIN")
               // .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
               // .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll().and().httpBasic()
                //.equals(new AntPathRequestMatcher("/logout"))
                //.logoutSuccessUrl("/login")
                .and().cors().and().csrf()
                .disable();
  *///

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

