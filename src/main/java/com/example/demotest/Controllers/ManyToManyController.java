package com.example.demotest.Controllers;

import com.example.demotest.Models.GameAwards;
import com.example.demotest.Models.Games;
import com.example.demotest.repo.GameAwardsRepository;
import com.example.demotest.repo.GamesAndAwardRepository;
import com.example.demotest.repo.GamesRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManyToManyController {

    @Autowired
    private GamesAndAwardRepository gamesAndAwardRepository;

    @Autowired
    private GameAwardsRepository gameAwardsRepository;



    @GetMapping("/games/awards")
    private String Main(Model model)
    {
        Iterable<Games> games = gamesAndAwardRepository.findAll();
        model.addAttribute("games", games);

        Iterable<GameAwards> awards = gameAwardsRepository.findAll();
        model.addAttribute("awards",awards);

        return "games-awards";
    }

    @PostMapping("/games/awards")
    public String addGameAward(
            @RequestParam String game,
            @RequestParam String award,
            Model model

    )
    {

        Iterable<Games> games = gamesAndAwardRepository.findAll();
        model.addAttribute("games", games);

        Iterable<GameAwards> awards = gameAwardsRepository.findAll();
        model.addAttribute("awards",awards);

        GameAwards award2 = gameAwardsRepository.findByName(award);
        Games games2 = gamesAndAwardRepository.findByTitle(game);

        award2.getGames().add(games2);
        games2.getAwards().add(award2);


        gamesAndAwardRepository.save(games2);
        gameAwardsRepository.save(award2);

        return "games-awards";
    }

}
