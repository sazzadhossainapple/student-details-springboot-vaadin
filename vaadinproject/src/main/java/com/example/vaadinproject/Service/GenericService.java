package com.example.vaadinproject.Service;

import java.util.List;

public interface GenericService<T,I> {
    List<T> findAll();
    T findById(I i);
    T save(T t);
    void delete(T t);

}
