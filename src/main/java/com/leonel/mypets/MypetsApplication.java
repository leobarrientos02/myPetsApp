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
		User leo = new User("leobarrientos02", "password", "Leonel", "Barrientos", "https://avatars.githubusercontent.com/u/77355023?v=4");
		Pet jasper2 = new Pet( "Jasper", "Jasper is my loving cat that everyone falls in love with him when they meet him.", "Cat", "American Shorthair", "Male", "https://www.thesprucepets.com/thmb/ac8-PKFO6U6I6rF52bPlJDD1onM=/1471x980/filters:fill(auto,1)/GettyImages-1288261359-4016b054680e41d28451f081babd0c45.jpg");
		jasper2.setUser(leo);

		session.save(leo);
		session.save(jasper2);

		transactionManager.commitTransaction(tx);//unnecessary over-engineering?
	}

}
