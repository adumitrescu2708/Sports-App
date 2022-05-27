package com.form.repositories;

import com.form.model.users.User;
import com.form.model.utils.Pair;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * MVC - User Repository
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
@Repository
public class UserRepository implements com.form.repositories.Repository<User> {
    /** Uses a hashmap to store users by a pair of username and password */
     private Map<Pair<String, String>, User> repository = new HashMap<>();

     /**
      * Searches in the hashmap for the given pair of username and password
      * */
    public User findByUsernameAndPassword(String username, String password) {
        Pair<String, String> data = new Pair<>(username, password);
        return repository.get(data);
    }

    /**
     * Adds the user in the hashmap.
     * */
    public User save(User user) {
        Pair<String, String> data = new Pair<>(user.getUsername(), user.getPassword());
        repository.put(data, user);
        return user;
    }

}
