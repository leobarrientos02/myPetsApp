package com.leonel.mypets.beans.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StorageManager implements Lifecycle {

    private boolean running = false;

    private final List<Class> models;
    private final Configuration config;
    private SessionFactory sessionFactory;
    private Session session;

    public StorageManager() {
        config = new Configuration();
        sessionFactory = config.buildSessionFactory();
        models = new LinkedList<>();
    }

    @Override
    public void start() {
        for(Class entity : models) {
            config.addAnnotatedClass(entity);
        }
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
        session.close();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public List<Class> getModels() {
        return models;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void addModel(Class model) {
        models.add(model);
    }

}
