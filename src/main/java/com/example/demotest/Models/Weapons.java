package com.example.demotest.Models;


import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Weapons {

    @Id
    @GeneratedValue//автоинкремент
    private Long id;

    @NonNull
    private String title;
    private  boolean fire;
    private int uses;
    private String info;
    private  double price;

    public Weapons(@NonNull String title, boolean fire, int uses, String info, double price) {
        this.title = title;
        this.fire = fire;
        this.uses = uses;
        this.info = info;
        this.price = price;
    }
    public Weapons() {
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
    public boolean isFire() {
        return fire;
    }
    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
