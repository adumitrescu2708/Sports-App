package com.form.model.data.locations;

import com.form.model.utils.Node;

/**
 * State - Node in the Tree with City children
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class State extends Node<City> {
    private String name;

    public State(String name) {
        this.name = name;
    }

    /** Search for a child in the children list */
    @Override
    public City searchChild(String name) {
        for(City city : children) {
            if(city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
