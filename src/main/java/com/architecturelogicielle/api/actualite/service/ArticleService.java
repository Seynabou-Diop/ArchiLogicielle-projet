package com.architecturelogicielle.actualite.service;

import com.architecturelogicielle.actualite.model.Article;
import com.architecturelogicielle.actualite.model.Category;
import com.architecturelogicielle.actualite.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepo;

    public Iterable<Article> getArticles(){
        return articleRepo.findAll();
    }

    public Optional<Article> getArticle(final Long id){
        return articleRepo.findById(id);
    }

    public Iterable<Article> getArticlesByCategory(final Category id){
        return articleRepo.findByCategorie(id);
    }

    public Article saveArticle(Article article){
        return articleRepo.save(article);
    }

    public Article modifier(Long id, Article article) {

        return articleRepo.findById(id)
                .map(art-> {
                    art.setTitre(article.getTitre());
                    art.setContenu(article.getContenu());
                    art.setDateCreation(article.getDateCreation());
                    art.setDateModification(LocalDateTime.now());
                    return articleRepo.save(art);
                }).orElseThrow(()-> new RuntimeException("Article Introuvable!"));

    }

    public void deleteArticle(final Long id){
        articleRepo.deleteById(id);
    }



}
