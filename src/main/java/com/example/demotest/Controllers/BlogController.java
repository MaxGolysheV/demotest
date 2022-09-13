package com.example.demotest.Controllers;


import com.example.demotest.Models.*;
import com.example.demotest.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


        return "posts";
    }

    @GetMapping("/blog")
    public String posts(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "posts";
    }
    @GetMapping("/games")
    public String gamesMain(Model model)
    {
        Iterable<Games> games = gamesRepository.findAll();
        model.addAttribute("games",games);
        return "games";
    }


    @GetMapping("/weapons")
    public String weaponsMain(Model model)
    {
        Iterable<Weapons> weapons = weaponRepository.findAll();
        model.addAttribute("weapons",weapons);
        return "weapons";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Post post, Model model) {return "blog-add";}

    /*@PostMapping("/blog/add")
    public  String blogPostAdd(
            @RequestParam(required = false, defaultValue = "Example") String title,
            @RequestParam(required = false, defaultValue = "Example") String anons,
            @RequestParam(required = false, defaultValue = "Example") String full_text,
            Model model
    ){

        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }*/

    @PostMapping("/blog/add")
    public  String blogPostAdd(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult
    //valid - аннотация валидации, проверяющая правильность ввода в зависимости от условий в модели
    // bindingResult - проверка на ошибки при ааннотации @Valid
    ){
       if(bindingResult.hasErrors())
       {
           return "blog-add";
       }
        postRepository.save(post);
        return "redirect:/blog";
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
    public String blogDetails(

            @PathVariable(value="id") long id, Model model)
    {
        Post pcs = postRepository.findById(id);
            model.addAttribute("post", pcs);
            return "blog-details";
    }

    //править статью
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(
            @ModelAttribute("post") Post post,//данные
            @PathVariable("id") long id,
                           Model model )
    {
        if(!postRepository.existsById(id))
        {return "redirect:/blog";}
        Post p = postRepository.findById(id);
        model.addAttribute("post_values",p);
        return "blog-edit";
    }

//обновление статьи
   /* @PostMapping("/blog/{id}/edit")
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
    }*/

    @PostMapping("/blog/{id}/edit")
    public String blogUpdate(
            @ModelAttribute("post") @Valid Post posts,
            BindingResult bindingResult,
                             @PathVariable("id")long id,
            Model model)
    {
        if(bindingResult.hasErrors())
        {
            Post p = postRepository.findById(id);
            model.addAttribute("post_values",p);
            return "blog-edit";
        }
        long i = posts.getId();
        Post rPost = postRepository.findById(i);
        rPost.setTitle(posts.getTitle());
        rPost.setAnons(posts.getAnons());
        rPost.setFull_text(posts.getFull_text());
        postRepository.save(rPost);
        return "redirect:/blog";
    }



    //удаление статьи
    @PostMapping("/blog/{id}/delete")
    public String deletePC(@PathVariable("id") long id, Model model){

        Post pcs = postRepository.deleteById(id);
        return "redirect:/blog";

    }


    //ПР2
    //игры

    //добавить игру
        @GetMapping("/blog/gamesadd")
        public String games (Games games, Model model)
        {
            return "games-add";
        }

        /*@PostMapping("/blog/gamesadd")
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
            return "redirect:/games";
        }*/

    @PostMapping("/blog/gamesadd")
    public String gamesAdd (@ModelAttribute("games") @Valid Games games, BindingResult bindingResult
    )
    {
        if(bindingResult.hasErrors()) {
             return "games-add";
        }
        gamesRepository.save(games);
        return "redirect:/games";
    }
        @GetMapping("/blog/gamesfilter")
        public String gamesFilter (Model model)
        {
            return "games-filter";
        }
        @PostMapping("blog/gamesfilter/result")
        public String gamesFilterResult (
            @RequestParam String title,
        @RequestParam int rule,
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
        Games pcs = gamesRepository.findById(id_game);
        model.addAttribute("game", pcs);
        return "games-details";
    }
    // изменение игры
    @GetMapping("/games/{id_game}/edit")
    public String gamesEdit(
            @ModelAttribute("game") Games game,
            @PathVariable("id_game") long id_game,
            Model model )
    {
        if(!gamesRepository.existsById(id_game))
        {return "redirect:/games";}
        Games g = gamesRepository.findById(id_game);
        model.addAttribute("game_values",g);
        return "games-edit";
    }
    //обновление игры
   /* @PostMapping("/games/{id_game}/edit")
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
        return "redirect:/games";
    }*/
    @PostMapping("/games/{id_game}/edit")
    public String gamesUpdate(
            @ModelAttribute("game") @Valid  Games game,
                             BindingResult bindingResult,
                             @PathVariable("id_game")long id_game, Model model
    )
    {


       if(bindingResult.hasErrors())
       {
           if(!gamesRepository.existsById(id_game))
           {return "redirect:/games";}
           Games g = gamesRepository.findById(id_game);
           model.addAttribute("game_values",g);
           return "games-edit";
       }

       long i = id_game;
       Games rGame = gamesRepository.findById(i);

       rGame.setTitle(game.getTitle());
       rGame.setPublisher(game.getPublisher());
       rGame.setDate(game.getDate());
       rGame.setGenre(game.getGenre());
       rGame.setAge(game.getAge());

        gamesRepository.save(rGame);
        return "redirect:/games";
    }

    //Удаление игры
    @PostMapping("/games/{id_game}/delete")
    public String gameDelete(@PathVariable("id_game") long id_game, Model model){
        gamesRepository.deleteById(id_game);
        return "redirect:/games";
    }


    //Оружейник
    @Autowired
    private WeaponRepository weaponRepository;

    @GetMapping("/blog/weapons")
    public String weapons(Weapons weapons, Model model)
    {
        Iterable<WeaponMaker> maker = weaponMakerRepository.findAll();//
        model.addAttribute("maker",maker);

        return "weapon-add";
    }

   /* @PostMapping("/blog/weapons")
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
        return "redirect:/weapons";
    }*/

    @PostMapping("/blog/weapons")
    public String weaponAdd(@ModelAttribute("weapons") @Valid Weapons weapons,
                            @RequestParam String maker,
                            BindingResult bindingResult)//
    {
        if(bindingResult.hasErrors())
        {
            return "weapon-add";

        }
        weapons.setWeaponMaker(weaponMakerRepository.findByName(maker));

        weaponRepository.save(weapons);
        return "redirect:/weapons";
    }

    @GetMapping("/blog/weaponfilter")
    public String weaponFilter(Model model)
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

    //подробнее об оружии
    @GetMapping("/weapons/{id_weapon}")
    public String weaponsDetails(@PathVariable(value="id_weapon") long id_weapon, Model model)
    {
       Weapons w = weaponRepository.findById(id_weapon);
       model.addAttribute("weapon", w);
       return "weapons-details";
    }



    //изменение данных оружия
    @GetMapping("/weapons/{id_weapon}/edit")
    public String weaponsEdit(
            @ModelAttribute("weapon") Weapons weapon,

            @PathVariable("id_weapon") long id_weapon,
            Model model )
    {

        if(!weaponRepository.existsById(id_weapon))
        {return "redirect:/weapons";}

        Iterable<WeaponMaker> maker = weaponMakerRepository.findAll();//
        model.addAttribute("maker",maker);

        long i = id_weapon;
        Weapons w = weaponRepository.findById(i);
        model.addAttribute("weapon_value",w);

        return "weapons-edit";
    }


    //обновление оружия
    @PostMapping("/weapons/{id_weapon}/edit")
    public String weaponsUpdate(
            @ModelAttribute("weapon") @Valid Weapons weapon,
            BindingResult bindingResult,
            @RequestParam String maker,
            @PathVariable("id_weapon")long id_weapon
            ,Model model)
    {

        if(bindingResult.hasErrors())
        {
            if(!weaponRepository.existsById(id_weapon))
            {return "redirect:/weapons";}
            long i = id_weapon;
            Weapons w = weaponRepository.findById(i);
            model.addAttribute("weapon_value",w);
            return "weapons-edit";
        }

        long i = id_weapon;
        Weapons rWep = weaponRepository.findById(i);

        rWep.setTitle(weapon.getTitle());
        rWep.setFire(weapon.isFire());
        rWep.setUses(weapon.getUses());
        rWep.setInfo(weapon.getInfo());
        rWep.setPrice(weapon.getPrice());


        rWep.setWeaponMaker(weaponMakerRepository.findByName(maker));//

        weaponRepository.save(rWep);
        return "redirect:/weapons";
    }
    //удаление оружия
    @PostMapping("/weapons/{id_weapon}/delete")
    public String weaponsDelete(@PathVariable("id_weapon") long id_weapon, Model model){
        weaponRepository.deleteById(id_weapon);
        return "redirect:/weapons";
    }



    //Many to One

    @Autowired
    public WeaponMakerRepository weaponMakerRepository;


    //One to one

    @Autowired
    private LicenceRepository licenceRepository;





    @GetMapping("/weapons/maker")
    public String makerLicence(Model model)
    {
        List<WeaponMakerLicence> pasport = licenceRepository.findAll();

        int siz=0;
        for (siz=pasport.size()-1;siz>=0;siz--){if (pasport.get(siz).getMaker()!=null){pasport.remove(siz);}}


        model.addAttribute("pasport", pasport);

        return "weapon-maker";
    }

    @PostMapping("/weapons/maker")
    public String blogPostAdd(
            @RequestParam String lname,//
            @RequestParam String mname,//
            Model model)
    {

      /*  Iterable<WeaponMaker> maker = weaponMakerRepository.findAll();
        model.addAttribute("pasport", maker);//заполнение списка мастеров
        WeaponMaker makers = weaponMakerRepository.findByName(mname);
        WeaponMakerLicence licence = new WeaponMakerLicence(lname,makers);


        licence.setMaker(weaponMakerRepository.findByName(mname));
        weaponMakerRepository.save(makers);
        licenceRepository.save(licence);*/

        Iterable<WeaponMakerLicence> licences = licenceRepository.findAll();
        model.addAttribute("pasport", licences);

        WeaponMakerLicence lic = licenceRepository.findByName(lname);

        WeaponMaker maker = new WeaponMaker(mname,lic);

        maker.setLicence(licenceRepository.findByName(lname));
        lic.setMaker(maker);
        //licenceRepository.save(lic);
        weaponMakerRepository.save(maker);



        return "redirect:/weapons";

    }

}
