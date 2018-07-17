package wbdv.services;

import wbdv.models.User;
import wbdv.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
public class UserService {


    @Autowired
    UserRepository userRepository;

    //    Find all users
    @GetMapping("/api/user")
    // https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    //Create a user
    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //    Find a user by id
    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);

    }

    //Delete a user
    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userRepository.deleteById(id);
    }

    //Update a user
    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int id, @RequestBody User updatedUser) {
        Optional<User> optional = userRepository.findById(id);
        System.out.println(updatedUser);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setUsername(updatedUser.getUsername());
            user.setLastName(updatedUser.getLastName());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            user.setPhoneNo(updatedUser.getPhoneNo());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            System.out.println("***********");
            System.out.println(user);
            return userRepository.findById(id).get();
        }
        return null;
    }

    // Find user by username

    private Iterable<User> findUserByUsername(@RequestParam(name = "username", required = false) String username) {
        return userRepository.findUserByUserName(username);
    }

    // Login functionality
    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session) {
        Iterable<User> currentUser = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
        if (currentUser.iterator().hasNext()) {
            User u = currentUser.iterator().next();
            session.setAttribute("currentUser", u);
            return userRepository.findById(u.getId()).get();
        }
        return null;
    }


    //Register functionality
    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session) {
        Iterable<User> currentUser = findUserByUsername(user.getUsername());
        if (!currentUser.iterator().hasNext()) {
            User u = createUser(user);
            session.setAttribute("currentUser", u);
            return userRepository.findById(u.getId()).get();
        }
        return null;
    }

    //Profile session management
    @GetMapping("/api/profile")
    public User profile(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        return userRepository.findById(currentUser.getId()).get();
    }

    //Update profile
    @PutMapping("/api/profile")
    public User updateProfile(@RequestBody User user, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        user.setId(currentUser.getId());
        user.setPassword(currentUser.getPassword());
        userRepository.save(user);
        User test= findUserById(user.getId());
        System.out.println(user.getDateOfBirth());
        return test;
    }


    //Logout functionality
    @PostMapping("/api/logout")
    public User logout(HttpSession session) {
        session.invalidate();
        return null;
    }

}