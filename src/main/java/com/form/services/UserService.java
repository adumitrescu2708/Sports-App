package com.form.services;


import com.form.model.enums.Role;
import com.form.model.users.Admin;
import com.form.model.users.User;
import com.form.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * MVC - User Repository
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
@Service
public class UserService {
    /** User Repository - Singleton, Constructor Injected */
    private final UserRepository userRepository;

    /** Constructor */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Register Request
     * <p>
     *     Searches for the registered data in the user repository.
     *     If the user is already registered, returns an error.
     *     Otherwise, save the new user in the User repository.
     * </p>
     * */
    public User registerRequest(String username, String password, Role role) {
        if(userRepository.findByUsernameAndPassword(username, password) != null) {
            return null;
        }
        if(username != null && password != null && role != null) {
            User newUser;
            switch (role) {
                case USER:
                    newUser = new User();
                    break;
                case ADMIN:
                    newUser = new Admin();
                    break;
                default:
                    newUser = null;
            }
            newUser.setPassword(password);
            newUser.setUsername(username);
            newUser.setRole(role);
            return userRepository.save(newUser);
        }
        return null;
    }

    /**
     * Login Request
     * <p>
     *     Searches the data in the user repository and returns
     *     the data.
     * </p>
     * */
    public User loginRequest(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }


}
