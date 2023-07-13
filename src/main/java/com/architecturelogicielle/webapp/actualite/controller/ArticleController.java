package com.architecturelogicielle.webapp.actualite.controller;

import com.architecturelogicielle.webapp.actualite.model.Article;
import com.architecturelogicielle.webapp.actualite.model.Category;
//import com.architecturelogicielle.webapp.actualite.repository.UserProxy;
import com.architecturelogicielle.webapp.actualite.model.Utilisateur;
import com.architecturelogicielle.webapp.actualite.repository.UserProxy;
import com.architecturelogicielle.webapp.actualite.service.ArticleService;
import com.architecturelogicielle.webapp.actualite.service.CategoryService;

import com.architecturelogicielle.webapp.actualite.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Controller
public class ArticleController {
//    @Autowired
//    private UserProxy authenticationProxy;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        Article recentArticle = articleService.getMostRecentArticle();
        Iterable<Category> categories = categoryService.getAllCategories();

        Article previousArticle = articleService.getPreviousArticle(recentArticle);
        Article nextArticle = articleService.getNextArticle(recentArticle);

        model.addAttribute("article", recentArticle);
        model.addAttribute("categories", categories);
        model.addAttribute("previousArticleId", previousArticle != null ? previousArticle.getId() : null);
        model.addAttribute("nextArticleId", nextArticle != null ? nextArticle.getId() : null);

        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/dashboard-admin")
//    public String dashboard(Model model) {
//        List<Utilisateur> utilisateurs = userService.getAllUtilisateurs();
//        Iterable<Article> articles = articleService.getAllArticles();
//        Iterable<Category> categories = categoryService.getAllCategories();
//
//        model.addAttribute("utilisateurs", utilisateurs);
//        model.addAttribute("articles", articles);
//        model.addAttribute("categories", categories);
//
//        return "dashboard-admin";
//    }

    @GetMapping("/article/{id}/view")
    public String viewArticle(@PathVariable("id") int id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article";
    }


    @GetMapping("/article/{id}")
    @ResponseBody
    public Map<String, Object> articleDetail(@PathVariable("id") int id) {
        Article article = articleService.getArticleById(id);
        Article previousArticle = articleService.getPreviousArticle(article);
        Article nextArticle = articleService.getNextArticle(article);

        Map<String, Object> response = new HashMap<>();
        response.put("article", article);
        response.put("previousArticleId", previousArticle != null ? previousArticle.getId() : null);
        response.put("nextArticleId", nextArticle != null ? nextArticle.getId() : null);

        return response;
    }


    @GetMapping("/categorie/{categoryId}")
    public String articlesByCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        Iterable<Article> articles = articleService.getArticlesByCategory(category);
        Iterable<Category> categories = categoryService.getAllCategories();
        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);
        return "category_articles";
    }


    @GetMapping("/articles")
    public String getAllArticles(Model model) {
        Iterable<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "article_list";
    }

    @GetMapping("/article/create")
    public String createArticleForm(Model model) {
        Article article = new Article();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("article", article);
        model.addAttribute("categories", categories);
        return "article_create_form";
    }

    @PostMapping("/article/create")
    public String createArticle(@ModelAttribute("article") @Validated Article article, BindingResult result, @RequestParam("categorie") String categorieId) {
        if (result.hasErrors()) {
            // Gérer les erreurs de validation
            return "article_create";
        }

        // Convertir l'identifiant de la catégorie en un objet Category
        Category category = categoryService.getCategoryById(Integer.parseInt(categorieId));
        article.setCategorie(category);
        articleService.saveArticle(article);
        return "redirect:/articles";
    }



    @GetMapping("/article/edit/{id}")
    public String editArticleForm(@PathVariable("id") int id, Model model) {
        Article article = articleService.getArticleById(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("article", article);
        model.addAttribute("categories", categories);
        return "article_edit_form";
    }



    @PostMapping("/article/update/{id}")
    public String updateArticle(@PathVariable("id") int id, @ModelAttribute("article") Article article, @RequestParam("categorie") String categorieId) {
        // Convert the categorieId to an integer and set it to the article
        article.setId(id);
        int categoryId = Integer.parseInt(categorieId);
        Category category = categoryService.getCategoryById(categoryId);
        article.setCategorie(category);
        
        articleService.updateArticle(article);
        return "redirect:/articles";
    }


    @GetMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable("id") int id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }
}