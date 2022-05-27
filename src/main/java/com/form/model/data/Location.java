package com.form.model.data;

/**
 * Location Prototype
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class Location {
    /** Country */
    private String country;
    /** State */
    private String state;
    /** City */
    private String city;
    /** Address */
    private String address;
    /** Constructor */
    public Location() {

    }
    /** Getter and Setter */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
