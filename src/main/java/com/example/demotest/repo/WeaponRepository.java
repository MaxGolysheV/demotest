package com.example.demotest.repo;

import com.example.demotest.Models.Games;
import com.example.demotest.Models.Post;
import com.example.demotest.Models.Weapons;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponRepository extends CrudRepository<Weapons,Long> {

    List<Weapons> findByTitle(String title);// поиск по точному названию

    List<Weapons> findByTitleContains(String title);// поиск по символам и содержимому
}
