package com.example.demotest.repo;

import com.example.demotest.Models.Games;
import org.springframework.data.repository.CrudRepository;

public interface GamesAndAwardRepository extends CrudRepository<Games,Long> {

    Games findByTitle(String title);

}
