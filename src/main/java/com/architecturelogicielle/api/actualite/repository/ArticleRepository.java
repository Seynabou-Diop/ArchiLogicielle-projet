package com.architecturelogicielle.actualite.repository;

import com.architecturelogicielle.actualite.model.Article;
import com.architecturelogicielle.actualite.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategorie(Category categorie);

}
