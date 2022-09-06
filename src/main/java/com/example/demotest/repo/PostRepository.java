package com.example.demotest.repo;

import com.example.demotest.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long>{
    //List<Post> findByTitle(String title);// поиск по точному названию

    List<Post> findByTitleContains(String title);// поиск по символам и содержимому

}
