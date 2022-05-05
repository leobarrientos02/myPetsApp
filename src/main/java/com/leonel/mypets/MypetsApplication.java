package com.leonel.mypets;

import com.leonel.mypets.models.Pet;
import com.leonel.mypets.models.User;
import com.leonel.mypets.utilities.StorageManager;
import com.leonel.mypets.utilities.TransactionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MypetsApplication {

	public static void main(String[] args) {

		SpringApplication.run(MypetsApplication.class, args);

		StorageManager storageManager = new StorageManager();
		storageManager.addAnnotatedClass(Pet.class);
		storageManager.addAnnotatedClass(User.class);

		Session session = storageManager.initializeDataSource();

		TransactionManager transactionManager = new TransactionManager(session);

		Transaction tx = transactionManager.beginTransaction();
		User leo = new User(1,"leobarrientos02", "password", "Leonel", "Barrientos", "https://avatars.githubusercontent.com/u/77355023?v=4");

		session.save(leo);

		transactionManager.commitTransaction(tx);//unnecessary over-engineering?
	}

}
