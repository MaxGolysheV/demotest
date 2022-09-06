package com.example.demotest.Models;


import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Games {
    @Id
    @GeneratedValue//автоинкремент
    private Long id;
    @NonNull
    private String title;
    private String publisher;
    private Date date;
    private String genre;
    private int age;
    public Games(@NonNull String title, String publisher, Date date, String genre, int age) {
        this.title = title;
        this.publisher = publisher;
        this.date = date;
        this.genre = genre;
        this.age = age;
    }
    public Games() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

