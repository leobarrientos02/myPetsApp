package com.leonel.mypets.repositories;

import com.leonel.mypets.models.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class PetRepo implements HibernateRepo<Pet> {

    private Session session;

    public PetRepo(Session session){
        this.session = session;
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
}
