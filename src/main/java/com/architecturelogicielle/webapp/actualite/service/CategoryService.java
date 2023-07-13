package com.architecturelogicielle.webapp.actualite.service;

import com.architecturelogicielle.webapp.actualite.model.Category;
import com.architecturelogicielle.webapp.actualite.repository.CategoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryProxy categoryProxy;

    public List<Category> getAllCategories() {
        return categoryProxy.getAllCategories();
    }

    public Category getCategoryById(int id) {
        List<Category> categories = categoryProxy.getAllCategories();
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Category createCategory(Category category) {
        return categoryProxy.createCategory(category);
    }

    public Category updateCategory(Category category) {
        return categoryProxy.updateCategory(category);
    }

    public void deleteCategory(long id) {
        categoryProxy.deleteCategory(id);
    }
}
