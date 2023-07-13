package com.architecturelogicielle.webapp.actualite.controller;

import com.architecturelogicielle.webapp.actualite.model.Utilisateur;
import com.architecturelogicielle.webapp.actualite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<Utilisateur> users = userService.getAllUtilisateurs();
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/user/create")
    public String createUserForm(Model model) {
        Utilisateur user = new Utilisateur();
        model.addAttribute("user", user);
        return "user_create_form";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("user") Utilisateur user) {
        userService.createUtilisateur(user);
        return "redirect:user_list";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(@PathVariable("id") int id, Model model) {
        Utilisateur user = userService.getUtilisateurById(id);
        model.addAttribute("user", user);
        return "user_edit_form";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") Utilisateur user) {
        user.setId((long) id);
        userService.updateUtilisateur(user);
        return "redirect:user_list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUtilisateur(id);
        return "redirect:user_list";
    }
}