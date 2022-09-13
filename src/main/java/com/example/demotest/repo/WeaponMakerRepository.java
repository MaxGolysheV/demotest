package com.example.demotest.repo;

import com.example.demotest.Models.WeaponMaker;
import com.example.demotest.Models.Weapons;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface WeaponMakerRepository extends CrudRepository<WeaponMaker,Long> {

    WeaponMaker findByName(String name);

    List<WeaponMaker> findAll();

}
