package com.form.model.utils;

import java.util.Objects;
/**
 * Pair - Common Generic Pair Class
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class Pair <T, E>{
    public T x;
    public E y;

    public Pair(T x, E y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(x, pair.x) && Objects.equals(y, pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
