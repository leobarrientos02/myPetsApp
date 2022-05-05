package com.leonel.mypets.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "pets")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private String breed;

    @Column
    private String gender;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

   public Pet(){
   }

    public Pet(String name, String description, String type, String breed, String gender, String imageUrl) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.breed = breed;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }

    public Pet(User user, String name, String description, String type, String breed, String gender, String imageUrl){
       this.user = user;
        this.name = name;
        this.description = description;
        this.type = type;
        this.breed = breed;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
