package com.example.demotest.repo;

import com.example.demotest.Models.WeaponMaker;
import com.example.demotest.Models.WeaponMakerLicence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicenceRepository extends CrudRepository<WeaponMakerLicence, Long> {

    WeaponMakerLicence findByName(String name);

    List<WeaponMakerLicence> findAll();
}
