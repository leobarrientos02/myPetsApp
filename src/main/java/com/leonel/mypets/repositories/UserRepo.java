package com.leonel.mypets.repositories;

import com.leonel.mypets.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class UserRepo {

    private Session session;
    String tableName;

    public UserRepo(Session session){
        this.session = session;
        this.tableName = "users";
    }

    public void save(User user){
        Transaction tx = session.beginTransaction();
        session.save(user);
    }

    public List<User> getAll(){
        String sql = "SELECT * FROM users";
        Query query = session.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();

        List<User> userList = new LinkedList<>();
        for(Object[] result : results){
            User user = new User();
            user.setId((Integer)result[0]);
            user.setFirstName((String)result[1]);
            user.setImageUrl((String)result[2]);
            user.setLastName((String)result[3]);
            user.setPassword((String)result[4]);
            user.setUsername((String)result[5]);
            userList.add(user);
        }
        return userList;
    }

}