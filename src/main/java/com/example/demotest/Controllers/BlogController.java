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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //подробнее остатье
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value="id") long id, Model model)
    {
        Optional<Post> pcs = postRepository.findById(id);
        if( pcs.isPresent() )
        {
            List<Post> res = new ArrayList<>();
            res.add(pcs.get());
            model.addAttribute("post", res);
            return "blog-details";
        }
        return "redirect:/";
    }

    //править статью
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") long id,Model model )
    {

        if(!postRepository.existsById(id))
        {return "redirect:/";}

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-edit";
    }
//обновление статьи
    @PostMapping("/blog/{id}/edit")
    public String blogUpdate(@PathVariable("id")long id,
                               @RequestParam String title,
                               @RequestParam String anons,
                               @RequestParam String full_text,
                               Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/";
    }
    //удаление статьи
    @PostMapping("/blog/{id}/delete")
    public String deletePC(@PathVariable("id") long id, Model model){
        Post pcs = postRepository.findById(id).orElseThrow();
        postRepository.delete(pcs);
        return "redirect:/";
    }



    //ПР2
    //игры

        @GetMapping("/blog/gamesadd")
        public String games (Model model)
        {
            return "games-add";
        }

        @PostMapping("/blog/gamesadd")
        public String gamesAdd (
            @RequestParam(required = false, defaultValue = "Example") String title,
            @RequestParam(required = false, defaultValue = "Example") String publisher,
            @RequestParam Date date,
            @RequestParam(required = false, defaultValue = "Example") String genre,
        @RequestParam int age
            ,Model model)
        {
            Games post = new Games(title, publisher, date, genre, age);
            gamesRepository.save(post);
            return "redirect:/";
        }
        @GetMapping("/blog/gamesfilter")
        public String gamesFilter (Model model)
        {
            return "games-filter";
        }
        @PostMapping("blog/gamesfilter/result")
        public String gamesFilterResult (
            @RequestParam String title,
        @RequestParam/*(defaultValue = "0")*/ int rule,
        Model model)
        {
            if (rule == 1) {
                List<Games> result = gamesRepository.findByTitle(title);
                model.addAttribute("result", result);
            } else if (rule == 0) {
                List<Games> result = gamesRepository.findByTitleContains(title);
                model.addAttribute("result", result);
            }
            return "games-filter";
        }


    //подробнее об игре
    @GetMapping("/games/{id_game}")
    public String gamesDetails(@PathVariable(value="id_game") long id_game, Model model)
    {
        Optional<Games> g = gamesRepository.findById(id_game);
        if( g.isPresent() )
        {
            List<Games> res = new ArrayList<>();
            res.add(g.get());
            model.addAttribute("game", res);
            return "games-details";
        }
        return "redirect:/";
    }
    // изменение игры
    @GetMapping("/games/{id_game}/edit")
    public String gamesEdit(@PathVariable("id_game") long id_game,Model model )
    {

        if(!gamesRepository.existsById(id_game))
        {return "redirect:/";}

        Optional<Games> game = gamesRepository.findById(id_game);
        ArrayList<Games> res = new ArrayList<>();
        game.ifPresent(res::add);
        model.addAttribute("game",res);
        return "games-edit";
    }
    //обновление игры
    @PostMapping("/games/{id_game}/edit")
    public String blogUpdate(@PathVariable("id_game")long id_game,
                             @RequestParam(required = false, defaultValue = "Example") String title,
                             @RequestParam(required = false, defaultValue = "Example") String publisher,
                             @RequestParam Date date,
                             @RequestParam(required = false, defaultValue = "Example") String genre,
                             @RequestParam int age
            ,Model model)
    {
        Games game = gamesRepository.findById(id_game).orElseThrow();
        game.setTitle(title);
        game.setPublisher(publisher);
        game.setDate(date);
        game.setGenre(genre);
        game.setAge(age);
        gamesRepository.save(game);
        return "redirect:/";
    }

    //Удаление игры
    @PostMapping("/games/{id_game}/delete")
    public String gameDelete(@PathVariable("id_game") long id_game, Model model){
        Games pcs = gamesRepository.findById(id_game).orElseThrow();
        gamesRepository.delete(pcs);
        return "redirect:/";
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
