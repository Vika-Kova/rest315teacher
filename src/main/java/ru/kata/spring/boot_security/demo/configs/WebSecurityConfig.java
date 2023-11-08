package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
//private  final  PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler,
                             PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.successUserHandler = successUserHandler;
        //this.passwordEncoder = passwordEncoder;

        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//?безопасность
                .authorizeRequests()
                //.antMatchers("/", "/index").permitAll()//antMatchers указ url-адреса "/" и "/index" разрешены всем
               // .antMatchers("/admin/**").hasRole("ADMIN")//В "/admin/**" могут заходить только юзеры с ролью "ADMIN"
              //  .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")//все
              //  .and().formLogin().successHandler(successUserHandler).permitAll()
               // .and()
                //.logout().logoutSuccessUrl("/login");
                . antMatchers("/login", "/error").permitAll()
                .antMatchers("/admin/**", "/adminApi/**").hasRole("ADMIN")
                .antMatchers("/userApi/**", "/user/**", "/userApi/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/process_login")
                .successHandler(successUserHandler)
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

    }


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //взвр. юзера в SecurityContext
    public DaoAuthenticationProvider authenticationProvider() {//?
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
     provider.setUserDetailsService(  userDetailsService);
    return provider;
    }

    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     //  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   //}

}