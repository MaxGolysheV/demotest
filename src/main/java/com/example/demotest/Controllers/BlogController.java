package com.example.demotest.Controllers;


import com.example.demotest.Models.Games;
import com.example.demotest.Models.Post;
import com.example.demotest.Models.Weapons;
import com.example.demotest.repo.GamesRepositiry;
import com.example.demotest.repo.PostRepository;
import com.example.demotest.repo.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GamesRepositiry gamesRepository;


    @GetMapping("/")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);

        Iterable<Games> games = gamesRepository.findAll();
        model.addAttribute("games",games);

        Iterable<Weapons> weapons = weaponRepository.findAll();
        model.addAttribute("weapons",weapons);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {return "blog-add";}

    @PostMapping("/blog/add")
    public  String blogPostAdd(
            @RequestParam(required = false, defaultValue = "Example") String title,
            @RequestParam(required = false, defaultValue = "Example") String anons,
            @RequestParam(required = false, defaultValue = "Example") String full_text,
            Model model
    ){

        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("blog/filter")
    public String blogFilter(Model model) {return "blog-filter";}


    @PostMapping("blog/filter/result")
    public String blogResult (
            @RequestParam String title,
            Model model)
    {
        List<Post> result = postRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "blog-filter";
    }

    //ПР2
    //игры
    @GetMapping("/blog/gamesadd")
    public String games(Model model)
    {
        return "games-add";
    }

    @PostMapping("/blog/gamesadd")
    public String gamesAdd(
            @RequestParam(required = false, defaultValue = "Example") String title,
            @RequestParam(required = false, defaultValue = "Example") String publisher,
            @RequestParam Date date,
            @RequestParam(required = false, defaultValue = "Example") String genre,
            @RequestParam int age
            ,Model model)
    {
        Games post = new Games(title,publisher,date,genre,age);
        gamesRepository.save(post);
        return "redirect:/";
    }
    @GetMapping("/blog/gamesfilter")
    public String gamesFilter(Model model)
    {
        return "games-filter";
    }
    @PostMapping("blog/gamesfilter/result")
    public String gamesFilterResult (
            @RequestParam String title,
            @RequestParam/*(defaultValue = "0")*/ int rule,
            Model model)
    {
        if(rule == 1)
        {
            List<Games> result = gamesRepository.findByTitle(title);
            model.addAttribute("result", result);
        }
        else if (rule == 0)
        {
            List<Games> result = gamesRepository.findByTitleContains(title);
            model.addAttribute("result", result);
        }
        return "games-filter";
    }

    //Оружейник
    @Autowired
    private WeaponRepository weaponRepository;
    @GetMapping("/blog/weapons")
    public String weapons(Model model)
    {
        return "weapon-add";
    }

    @PostMapping("/blog/weapons")
    public String weaponAdd(
            @RequestParam String title,
            @RequestParam(defaultValue = "false") boolean fire,
            @RequestParam int uses,
            @RequestParam String info,
            @RequestParam double price
            ,Model model)
    {
        Weapons w = new Weapons(title,fire,uses,info,price);
        weaponRepository.save(w);
        return "redirect:/";
    }


    @GetMapping("/blog/weaponfilter")
    public String weaponilter(Model model)
    {
        return "weapon-filter";
    }

    @PostMapping("blog/weaponfilter/result")
    public String weaponFilterResult (
            @RequestParam String title,
            @RequestParam int rule,
            Model model)
    {
        if(rule == 1)
        {
            List<Weapons> result = weaponRepository.findByTitle(title);
            model.addAttribute("result", result);

        }
        else if (rule == 0)
        {
            List<Weapons> result = weaponRepository.findByTitleContains(title);
            model.addAttribute("result", result);
        }
        return "weapon-filter";
    }
}
