package com.example.demotest.Models;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Weapons {

    @Id
    @GeneratedValue//автоинкремент
    private Long id;
    @NotEmpty(message = "Данные поля не могут быть пустыми")
    @Size(min=2,max=50, message = "Размер данного поля должен быть в диапазоне от 2 до 50")
    private String title;
    @NotNull
    private  boolean fire;
    @PositiveOrZero
    @NotNull (message = "Значение не должно быть пустым")
    private int uses;
    @NotEmpty(message = "Данные поля не могут быть пустыми")
    @Size(min=2,max=50, message = "Размер данного поля должен быть в диапазоне от 2 до 50")
    private String info;
    @Positive
    @NotNull (message = "Значение не должно быть пустым")
    private  double price;



    @ManyToOne(optional = true,//
            cascade = CascadeType.ALL)
    private WeaponMaker weaponMaker;



    public Weapons(String title,//
                   boolean fire,
                   int uses,
                   String info,
                   double price,
                   WeaponMaker weaponMaker) {
        this.title = title;
        this.fire = fire;
        this.uses = uses;
        this.info = info;
        this.price = price;
        this.weaponMaker = weaponMaker;
    }


    public WeaponMaker getWeaponMaker() {
        return weaponMaker;
    }

    public void setWeaponMaker(WeaponMaker weaponMaker) {
        this.weaponMaker = weaponMaker;
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
