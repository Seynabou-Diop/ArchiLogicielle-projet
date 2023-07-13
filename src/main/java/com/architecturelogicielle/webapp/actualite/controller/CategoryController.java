package com.architecturelogicielle.webapp.actualite.controller;

import com.architecturelogicielle.webapp.actualite.model.Category;
import com.architecturelogicielle.webapp.actualite.repository.UserProxy;
import com.architecturelogicielle.webapp.actualite.service.CategoryService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category_list";
    }

    @GetMapping("/category/create")
    public String createCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category_create_form";
    }

    @PostMapping("/category/create")
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/edit/{id}")
    public String editCategoryForm(@PathVariable("id") int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category_edit_form";
    }

    @PostMapping("/category/update/{id}")
    public String updateCategory(@PathVariable("id") int id, @ModelAttribute("category") Category category) {
        category.setId(id);
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}