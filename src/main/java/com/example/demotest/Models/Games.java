package com.example.demotest.Models;


import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
public class Games {
    @Id
    @GeneratedValue//автоинкремент
    private Long id;

    @NotEmpty(message = "Данные поля не могут быть пустыми")
    @Size(min=2,max=50, message = "Размер данного поля должен быть в диапазоне от 2 до 50")
    private String title;

    @NotEmpty(message = "Данные поля не могут быть пустыми")
    @Size(min=2,max=50, message = "Размер данного поля должен быть в диапазоне от 2 до 50")
    private String publisher;

    @Past
    @NotNull(message = "Данные поля не могут быть пустыми")
    private Date date;

    @NotEmpty(message = "Данные поля не могут быть пустыми")
    @Size(min=2,max=50, message = "Размер данного поля должен быть в диапазоне от 2 до 50")
    private String genre;

    @NotNull(message = "Данные поля не могут быть пустыми")
    @Positive (message = "Данные поля не могут быть мньше нуля")
    private int age;



    public Games(String title, String publisher, Date date, String genre, int age) {
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

