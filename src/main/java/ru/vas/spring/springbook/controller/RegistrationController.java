package ru.vas.spring.springbook.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vas.spring.springbook.model.User;
import ru.vas.spring.springbook.repository.jpa.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository repository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public RegistrationController(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping
    public String showForm() {
        return "registerForm";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        repository.save(form.toUser(encoder));
        return "redirect:/design";
    }

    @Data
    private static class RegistrationForm {

        private String username;
        private String password;
        private String fullname;
        private String street;
        private String city;
        private String state;
        private String zip;
        private String phone;

        protected User toUser(PasswordEncoder encoder){
            return new User(username, encoder.encode(password), fullname, street, city, state, zip, phone);
        }

    }
}
