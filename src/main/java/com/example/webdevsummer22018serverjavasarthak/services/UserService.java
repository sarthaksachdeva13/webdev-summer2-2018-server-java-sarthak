package com.example.webdevsummer22018serverjavasarthak.services;

import com.example.webdevsummer22018serverjavasarthak.models.User;
import com.example.webdevsummer22018serverjavasarthak.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@RestController
public class UserService {


    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user, HttpSession session) {

        User currentUser = userRepository.save(user);

        session.setAttribute("currentUser", currentUser);

        return currentUser;
    }

    @GetMapping("/profile")
    public Optional<User> profile(HttpSession session)
    {
        User currentUser = (User) session.getAttribute("currentUser");
        return userRepository.findById(currentUser.getId());
    }

    @GetMapping("/api/user/{userId}")
    public Optional<User> findUserByUserId(@PathVariable("userId") String userId){
        int id = Integer.parseInt(userId);
        return userRepository.findById(id);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user, HttpSession session) {
        user = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
        session.setAttribute("currentUser", user);
        return user;
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
