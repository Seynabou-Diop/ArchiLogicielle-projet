package com.architecturelogicielle.actualite.service;

import com.architecturelogicielle.actualite.model.Category;
import com.architecturelogicielle.actualite.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public Iterable<Category> getCategorys(){
        return categoryRepo.findAll();
    }

    public Optional<Category> getCategory(final Long id){
        return categoryRepo.findById(id);
    }

    public Category saveCategory(Category category){
        return categoryRepo.save(category);
    }

    public Category modifier(Long id, Category category) {

        return categoryRepo.findById(id)
                .map(cat-> {
                    cat.setLibelle(category.getLibelle());
                    return categoryRepo.save(cat);
                }).orElseThrow(()-> new RuntimeException("Category Introuvable!"));

    }

    public void deleteCategory(final Long id){
        categoryRepo.deleteById(id);
    }



}
