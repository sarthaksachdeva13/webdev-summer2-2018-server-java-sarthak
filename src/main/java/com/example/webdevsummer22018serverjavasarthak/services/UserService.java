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

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session) {
        User currentUser = userRepository.save(user);
        session.setAttribute("currentUser", currentUser);
        return currentUser;
    }

    @GetMapping("/api/profile")
    public Optional<User> profile(HttpSession session)
    {
        User currentUser = (User) session.getAttribute("currentUser");
        return userRepository.findById(currentUser.getId());
    }


    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session) {
        user = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
        session.setAttribute("currentUser", user);
        return user;
    }


    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int id, @RequestBody User newUser) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()) {
            User user = optional.get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setUsername(newUser.getUsername());
            if (newUser.getPassword() != null) {
                user.setPassword(user.getPassword());
            }
            user.setPhoneNo(newUser.getPhoneNo());
            user.setRole(newUser.getEmail());
            user.setEmail(newUser.getEmail());
            user.setDateOfBirth(newUser.getDateOfBirth());
            return userRepository.save(user);
        }
        return null;
    }


    @PutMapping("/api/profile")
    public User updateProfile(@RequestBody User newUser) {
        Optional<User> optional = userRepository.findById(newUser.getId());
        if (optional.isPresent()) {
            User updatedUser = optional.get();
            updatedUser.setUsername(newUser.getUsername());
            updatedUser.setPhoneNo(newUser.getPhoneNo());
            updatedUser.setRole(newUser.getRole());
            updatedUser.setEmail(newUser.getEmail());
            updatedUser.setDateOfBirth(newUser.getDateOfBirth());
            return userRepository.save(updatedUser);
        }
        return null;
    }

    @GetMapping("/api/user/{userId}")
    public Optional<User> findUserById(@PathVariable("userId") String userId){
        int id = Integer.parseInt(userId);
        return userRepository.findById(id);
    }


    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
