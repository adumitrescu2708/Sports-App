package com.form.model.enums;

/**
 * Dates Enum
 * <p>
 *     List of possible months, prevents the user
 *     in requesting a non existing month.
 * </p>
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public enum Period {
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December"),
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August");
    private final String name;

    Period(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
