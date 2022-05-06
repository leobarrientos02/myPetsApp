package com.leonel.mypets.repositories;

import java.util.List;

public interface HibernateRepo<T> {
    public void save(T t);
    public List<T> getAll();
    public T getById(Integer id);
}
