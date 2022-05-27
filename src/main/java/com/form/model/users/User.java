package com.form.model.users;

import com.form.model.enums.Role;
/**
 * User
 * <p>
 *     Prototype of a user.
 * </p>
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class User {
    /** Username */
    private String username;
    /** Password */
    private String password;
    /** Admin or Common User */
    private Role role;

    /** Getters and Setters */
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
