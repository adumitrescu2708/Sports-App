package com.form.repositories;

/**
 * Repository Interface
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public interface Repository<T> {
    T save(T t);
}
