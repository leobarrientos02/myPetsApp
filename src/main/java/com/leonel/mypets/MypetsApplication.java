package com.leonel.mypets;

import com.leonel.mypets.models.Pet;
import com.leonel.mypets.models.User;
import com.leonel.mypets.repositories.PetRepo;
import com.leonel.mypets.repositories.UserRepo;
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

		User Randy = new User("randyO", "Chente72", "Randy", "Orellana", "https://i.pinimg.com/736x/83/ea/ad/83eaad43361e7ef9a3b9b728d351f1fc--wwe-funny-randy-orton.jpg");
		session.save(Randy);


		session.save(leo);
		session.save(jasper2);

		transactionManager.commitTransaction(tx);//unnecessary over-engineering?

		Pet ginger = new Pet("Ginger", "Ginger use to be a lovely cat but as soon as i got Watson and Julian, he got jealous and now he is a mad cat. Still love him so much.", "Cat", "American Shorthair", "Male", "https://i.dailymail.co.uk/i/newpix/2018/07/03/17/4DE30C9F00000578-0-image-a-5_1530633800491.jpg");
		ginger.setUser(leo);
		tx = transactionManager.beginTransaction();
		leo.addPet(ginger);

		UserRepo userRepo = new UserRepo(session);

		List<User> users = userRepo.getAll();

		transactionManager.commitTransaction(tx);

		for(User u : users){
			System.out.println("Username: " + u.getUsername());
		}

		User userRandy = userRepo.getByUsername("randyO");
		System.out.println("User " + userRandy.getUsername() + " Id is " + userRandy.getId());

		PetRepo petRepo = new PetRepo(session);
		Pet pet = petRepo.getById(1);
		System.out.println("Pet: " + pet.getName() + " is a " + pet.getBreed() + " " + pet.getType());

		List<Pet> petList = petRepo.getAll();
		for(Pet p : petList) {
			System.out.println("Pet: " + p.getName() + " is a " + p.getBreed() + " " + p.getType());
		}

		Pet pet2 = petRepo.getById(2);
		System.out.println("Pet: " + pet2.getName() + " is a " + pet2.getBreed() + " " + pet2.getType());
	}

}
