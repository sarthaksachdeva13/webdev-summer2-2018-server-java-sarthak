package com.example.webdevsummer22018serverjavasarthak.services;

import com.example.webdevsummer22018serverjavasarthak.models.User;
import com.example.webdevsummer22018serverjavasarthak.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
public class UserService {


    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/user")
    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }


    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int id)
    {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);

    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id)
    {
        userRepository.deleteById(id);
    }


    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int id,@RequestBody User updatedUser) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent())
        {
            User user = optional.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setUsername(updatedUser.getUsername());
            user.setLastName(updatedUser.getLastName());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            user.setPhoneNo(updatedUser.getPhoneNo());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            return userRepository.findById(id).get();
        }
        return null;
    }


    private Iterable<User> findUserByUsername(@RequestParam(name = "username", required = false) String username)
    {
        return  userRepository.findUserByUserName(username);
    }


    @PostMapping("/api/login")
    public User login(@RequestBody User user,HttpSession session)
    {
        Iterable<User> currentUser = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
        if (currentUser.iterator().hasNext()){
            User u = currentUser.iterator().next();
            session.setAttribute("currentUser", u);
            return userRepository.findById(u.getId()).get();
        }
        return null;
    }

    @PostMapping("/api/register")
    public User register(@RequestBody User user,HttpSession session) {
        Iterable<User> currentUser = findUserByUsername(user.getUsername());
        if (!currentUser.iterator().hasNext())
        {
            User registeredUser = createUser(user);
            session.setAttribute("currentUser", registeredUser);
            return userRepository.findById(registeredUser.getId()).get();
        }
        return null;
    }

    @GetMapping("/api/profile")
    public User profile(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        return userRepository.findById(currentUser.getId()).get();
    }

    @PutMapping("/api/profile")
    public User updateProfile(@RequestBody User user, HttpSession session) {
        User currentUser =  (User) session.getAttribute("currentUser");
        user.setId(currentUser.getId());
        user.setPassword(currentUser.getPassword());
        userRepository.save(user);
        return findUserById(user.getId());
    }

    @PostMapping("/api/logout")
    public User logout(HttpSession session) {
        session.invalidate();
        return null;
    }

}