package com.form.model.data.locations;

import com.form.model.utils.Node;

import java.util.ArrayList;
import java.util.List;
/**
 * Country - Node in the Tree with State children
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class Country extends Node<State> {
    /** name */
    private String name;

    public Country(String name) {
        this.name = name;
    }

    /** Search for a child in the children list */
    @Override
    public State searchChild(String name) {
        for(State state : children) {
            if(state.getName().equals(name)) {
                return state;
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
