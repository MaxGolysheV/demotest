package com.example.demotest.repo;

import com.example.demotest.Models.GameAwards;
import org.springframework.data.repository.CrudRepository;

public interface GameAwardsRepository extends CrudRepository<GameAwards, Long> {

    GameAwards findByName(String name);
}
