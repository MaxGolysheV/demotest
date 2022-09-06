package com.example.demotest.repo;

import com.example.demotest.Models.Games;
import com.example.demotest.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GamesRepositiry extends CrudRepository<Games,Long> {

    List<Games> findByTitle(String title);// поиск по точному названию
    List<Games> findByTitleContains(String title);// поиск по символам и содержимому


}
