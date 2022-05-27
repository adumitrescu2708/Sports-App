package com.form.model.enums;
/**
 * Sports Enum
 * <p>
 *     List of possible sports, prevents the user
 *     in requesting a non existing sport. The list
 *     can and should be extended.
 * </p>
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public enum Sport {
    SKI("Ski"),
    SWIM("Swim"),
    SNOW_BOARD("Snow-Board"),
    CLIMBING("Climbing"),
    HOCKEY("Hockey"),
    RAFTING("Rafting"),
    SURFING("Surfing"),
    CANOE("Canoe"),
    SAILING("Sailing"),
    AUTO_RACING("Auto-Racing"),
    DIVING("Diving");


    private final String name;

    Sport(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
