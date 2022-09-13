package com.example.demotest.Models;


import javax.persistence.*;

@Entity
@Table(name = "licence")
public class WeaponMakerLicence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*@OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="maker_id")
    private WeaponMaker maker;*/

    @OneToOne(optional = true, mappedBy = "licence")//
    private WeaponMaker maker;

    public WeaponMakerLicence(String name, WeaponMaker licence) {
        this.name = name;
        this.maker = licence;
    }

    public WeaponMakerLicence() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponMaker getMaker() {
        return maker;
    }

    public void setMaker(WeaponMaker maker) {
        this.maker = maker;
    }
}
