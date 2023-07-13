package com.architecturelogicielle.webapp.actualite.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.architecturelogicielle.webapp.actualite.model.Category;
import com.architecturelogicielle.webapp.actualite.service.CategoryService;

@Component
public class CategoryConverter implements Converter<String, Category> {

    private final CategoryService categoryService;

    public CategoryConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category convert(String categoryId) {
        int id = Integer.parseInt(categoryId);
        return categoryService.getCategoryById(id);
    }
}

