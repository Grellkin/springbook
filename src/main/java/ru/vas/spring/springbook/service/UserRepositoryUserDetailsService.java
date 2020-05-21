package ru.vas.spring.springbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vas.spring.springbook.model.User;
import ru.vas.spring.springbook.repository.jpa.UserRepository;

@Service
@Qualifier("self")
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can`t find user with username " + username);
        }
        return user;
    }
}
