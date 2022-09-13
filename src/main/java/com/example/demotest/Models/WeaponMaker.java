package com.example.demotest.Models;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class WeaponMaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "weaponMaker",//
    fetch = FetchType.EAGER)
    private Collection<Weapons> weapons;


   /* @OneToOne(optional = true, mappedBy = "maker")
    private WeaponMakerLicence licence;*/

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="licence_id")
    private WeaponMakerLicence licence;

    public WeaponMaker(String name, WeaponMakerLicence licence) {
        this.name = name;
        this.licence = licence;
    }

    public WeaponMaker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Weapons> getWeapons() {
        return weapons;
    }

    public void setWeapons(Collection<Weapons> weapons) {
        this.weapons = weapons;
    }

    public WeaponMakerLicence getLicence() {
        return licence;
    }

    public void setLicence(WeaponMakerLicence licence) {
        this.licence = licence;
    }
}
