package com.form.model.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * Node - Generic Prototype
 * <p>
 *     Stores a list of generic children and describes
 *     the methods for adding a children and searching
 *     for a children having the given key.
 * </p>
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public abstract class Node<T> {
    protected List<T> children = new ArrayList<>();

    public abstract T searchChild(String name);

    public void addChild(T child) {
        children.add(child);
    }
}
