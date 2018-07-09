package com.example.webdevsummer22018serverjavasarthak.services;

import com.example.webdevsummer22018serverjavasarthak.models.User;
import com.example.webdevsummer22018serverjavasarthak.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserService {


    @Autowired
    UserRespository userRespository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRespository.save(user);
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) userRespository.findAll();
    }
}
