package com.leonel.mypets.beans.repositories;

import org.springframework.context.Lifecycle;

import java.util.List;

public interface HibernateRepo<T> extends Lifecycle {
    public void save(T t);
    public List<T> getAll();
    public T getById(Integer id);
    public void delete(T t);
}
