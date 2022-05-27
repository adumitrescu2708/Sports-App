package com.form.model.enums;

/**
 * Role Enum
 * <p>
 *     List of possible roles, can be extended
 * </p>
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public enum Role {
    ADMIN("Admin"),
    USER("User");
    private final String name;

    Role(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
