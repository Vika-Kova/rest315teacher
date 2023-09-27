package ru.kata.spring.boot_security.demo.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    // @Override
  //  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      //  User user = userRepository.findByUsername(userName)
      //          .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
      ///  return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
         //       getAuthorities(user));
   // }

  //  private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
    //    String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
     //   Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
     //   return authorities;
   // }
}
