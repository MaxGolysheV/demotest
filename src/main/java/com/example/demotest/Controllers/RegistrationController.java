package com.example.demotest.Controllers;
import com.example.demotest.Models.Role;
import com.example.demotest.Models.User;
import com.example.demotest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration()
    {
            return "registration";//
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model)
    {

        User userForm = userRepository.findByUsername(user.getUsername());
        if(userForm != null)
        {
            model.addAttribute("message", "Пользователь уже такой есть");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";//
    }

}
