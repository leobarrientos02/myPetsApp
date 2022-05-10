package com.leonel.mypets;

import com.leonel.mypets.beans.repositories.PetRepo;
import com.leonel.mypets.beans.repositories.UserRepo;
import com.leonel.mypets.beans.services.StorageManager;
import com.leonel.mypets.models.Pet;
import com.leonel.mypets.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;


@SpringBootApplication(scanBasePackages = "com.leonel.mypets.beans")
public class MypetsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(MypetsApplication.class, args);
		/*
		Here we are initializing our Hibernate session, and at this point because our hbm2ddl setting is set
		to true, all tables are dropped and re-created.
		 */
		StorageManager storageManager = context.getBean(StorageManager.class);
		storageManager.addModel(Pet.class);
		storageManager.addModel(User.class);

		context.start();
		Session session = storageManager.getSession();

		UserRepo userRepo = context.getBean(UserRepo.class);
		PetRepo petRepo = context.getBean(PetRepo.class);

		User leo = new User("leobarrientos02", "password", "Leonel", "Barrientos", "https://avatars.githubusercontent.com/u/77355023?v=4");
		User Randy = new User("randyO", "Chente72", "Randy", "Orellana", "https://i.pinimg.com/736x/83/ea/ad/83eaad43361e7ef9a3b9b728d351f1fc--wwe-funny-randy-orton.jpg");

		// pets
		Pet jasper = new Pet( "Jasper", "Jasper is my loving cat that everyone falls in love with him when they meet him.", "Cat", "American Shorthair", "Male", "https://www.thesprucepets.com/thmb/ac8-PKFO6U6I6rF52bPlJDD1onM=/1471x980/filters:fill(auto,1)/GettyImages-1288261359-4016b054680e41d28451f081babd0c45.jpg");
		Pet ginger = new Pet("Ginger", "Ginger use to be a lovely cat but as soon as i got Watson and Julian, he got jealous and now he is a mad cat. Still love him so much.", "Cat", "American Shorthair", "Male", "https://i.dailymail.co.uk/i/newpix/2018/07/03/17/4DE30C9F00000578-0-image-a-5_1530633800491.jpg");

		ginger.setUser(leo);
		jasper.setUser(leo);
		leo.addPet(jasper);
		leo.addPet(ginger);

		session.save(leo);
		session.save(Randy);

		Transaction tx = session.beginTransaction();
		Pet Shaggy = new Pet(Randy,"Shaggy", "Shaggy my lovely protective dog.","Dog", "German Shepherds", "Male", "https://bloximages.chicago2.vip.townnews.com/newburyportnews.com/content/tncms/assets/v3/editorial/a/e7/ae7c3e54-6fcb-11ec-849e-93c6076c655b/61d858053d34a.image.jpg?resize=397%2C500");
		Randy.addPet(Shaggy);
		tx.commit();

		List<User> users = userRepo.getAll();

		for(User u : users){
			System.out.println("Username: " + u.getUsername());
		}

		User userRandy = userRepo.getByUsername("randyO");
		System.out.println("User " + userRandy.getUsername() + " Id is " + userRandy.getId());

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