package com.architecturelogicielle.webapp.actualite.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architecturelogicielle.webapp.actualite.model.Article;
import com.architecturelogicielle.webapp.actualite.model.Category;
import com.architecturelogicielle.webapp.actualite.repository.ArticleProxy;

@Service
public class ArticleService {

    @Autowired
    private ArticleProxy articleProxy;

    public Article getArticleById(int id) {
        return articleProxy.getArticle(id);
    }

    public Iterable<Article> getAllArticles() {
        return articleProxy.getAllArticles();
    }

    public Iterable<Article> getArticlesByCategory(Category category) {
        return articleProxy.getArticlesByCategory(category);
    }

    public Article getMostRecentArticle() {
        Iterable<Article> articles = articleProxy.getAllArticles();

        return StreamSupport.stream(articles.spliterator(), false)
                .max(Comparator.comparing(Article::getDateCreation))
                .orElse(null);
    }

    public Article getPreviousArticle(Article article) {
        Iterable<Article> articles = articleProxy.getAllArticles();

        List<Article> sortedArticles = StreamSupport.stream(articles.spliterator(), false)
                .sorted(Comparator.comparing(Article::getDateCreation))
                .collect(Collectors.toList());

        int index = sortedArticles.indexOf(article);
        if (index > 0) {
            return sortedArticles.get(index - 1);
        } else {
            return null;
        }
    }

    public Article getNextArticle(Article article) {
        Iterable<Article> articles = articleProxy.getAllArticles();

        List<Article> sortedArticles = StreamSupport.stream(articles.spliterator(), false)
                .sorted(Comparator.comparing(Article::getDateCreation))
                .collect(Collectors.toList());

        int index = sortedArticles.indexOf(article);
        if (index < sortedArticles.size() - 1) {
            return sortedArticles.get(index + 1);
        } else {
            return null;
        }
    }

    public Iterable<Article> getRecentArticles(int startIndex, int pageSize) {
        Iterable<Article> articles = articleProxy.getAllArticles();

        List<Article> sortedArticles = StreamSupport.stream(articles.spliterator(), false)
                .sorted(Comparator.comparing(Article::getDateCreation).reversed())
                .collect(Collectors.toList());

        List<Article> pagedArticles = sortedArticles.subList(startIndex, Math.min(startIndex + pageSize, sortedArticles.size()));

        return pagedArticles;
    }

    public int countArticles() {
        Iterable<Article> articles = articleProxy.getAllArticles();
        return (int) StreamSupport.stream(articles.spliterator(), false).count();
    }

    public void deleteArticle(long id) {
        articleProxy.deleteArticle(id);
    }

    public Article updateArticle(Article article) {
        return articleProxy.updateArticle(article);
    }

    public Article saveArticle(Article article) {
        if (article.getId() == null) {
            return articleProxy.createArticle(article);
        } else {
            return articleProxy.updateArticle(article);
        }
    }
}

