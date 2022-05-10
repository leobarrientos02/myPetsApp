package com.leonel.mypets.beans.repositories;

import com.leonel.mypets.beans.services.StorageManager;
import com.leonel.mypets.models.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PetRepo implements HibernateRepo<Pet> {

    private final StorageManager storageManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public PetRepo(StorageManager storageManager){
        this.storageManager = storageManager;
    }

    @Override
    public void save(Pet pet) {
        Transaction tx = session.beginTransaction();
        session.save(pet);
        tx.commit();
    }

    @Override
    public List<Pet> getAll() {
        String hql = "From Pet";
        TypedQuery<Pet> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Pet getById(Integer id) {
        String hql = "FROM Pet WHERE id = :id";
        TypedQuery<Pet> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Pet pet) {
        Transaction tx = session.beginTransaction();
        session.delete(pet);
        tx.commit();
    }


    @Override
    public void start() {
        this.session = storageManager.getSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

}
