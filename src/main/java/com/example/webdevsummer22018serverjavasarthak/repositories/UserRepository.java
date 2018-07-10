package com.example.webdevsummer22018serverjavasarthak.repositories;

import com.example.webdevsummer22018serverjavasarthak.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
    User findUserByCredentials(String username, String password);

}
