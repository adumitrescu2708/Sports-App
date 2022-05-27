package com.form.model.data.locations;

import com.form.model.post.Post;
import com.form.model.utils.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * City - Node in the Tree with Post leaves
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class City extends Node<Post> {
    /** name */
    private String name;

    public City(String name) {
        this.name = name;
    }
    /** Search for a child in the children list */
    @Override
    public Post searchChild(String name) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
