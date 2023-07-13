package com.architecturelogicielle.webapp.actualite.controller;

import com.architecturelogicielle.webapp.actualite.model.Article;
import com.architecturelogicielle.webapp.actualite.model.Category;
import com.architecturelogicielle.webapp.actualite.model.Utilisateur;
import com.architecturelogicielle.webapp.actualite.repository.UserProxy;
import com.architecturelogicielle.webapp.actualite.service.ArticleService;
import com.architecturelogicielle.webapp.actualite.service.CategoryService;
import com.architecturelogicielle.webapp.actualite.service.UserService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Data
@Controller
public class DashboardController {
    @Autowired
    private UserProxy userProxy;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

//  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dashboard-admin")
    public String dashboard(Model model) {
        List<Utilisateur> utilisateurs = userProxy.getAllUtilisateurs();
        Iterable<Article> articles = articleService.getAllArticles();
        Iterable<Category> categories = categoryService.getAllCategories();

        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);

        return "dashboard-admin";
    }
    
    @GetMapping("/dashboard-editeur")
    public String dashboardEdit(Model model) {
        List<Utilisateur> utilisateurs = userProxy.getAllUtilisateurs();
        Iterable<Article> articles = articleService.getAllArticles();
        Iterable<Category> categories = categoryService.getAllCategories();

        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);

        return "dashboard-editeur";
    }
}
