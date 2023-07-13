package com.architecturelogicielle.webapp.actualite.repository;

import com.architecturelogicielle.webapp.actualite.CustomProperties;
import com.architecturelogicielle.webapp.actualite.auth.AuthenticationResponse;
import com.architecturelogicielle.webapp.actualite.controller.AuthenticationRequest;
import com.architecturelogicielle.webapp.actualite.model.Article;
import com.architecturelogicielle.webapp.actualite.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.architecturelogicielle.webapp.actualite.model.Role;
import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationProxy {

    @Autowired
    private CustomProperties props;

    @Autowired
    private RestTemplate restTemplate;

    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        String baseApiUrl = props.getApiUrl();
        String authenticateUrl = baseApiUrl + "/api/auth/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authenticationRequest, headers);

        ResponseEntity<AuthenticationResponse> responseEntity = restTemplate.exchange(
                authenticateUrl,
                HttpMethod.POST,
                requestEntity,
                AuthenticationResponse.class
        );

        return responseEntity.getBody();
    }


    public List<Role> getUserRoles(String accessToken) {
        String apiUrl = props.getApiUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Role>> responseEntity = restTemplate.exchange(
                apiUrl + "/api/user/role",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Role>>() {}
        );

        return responseEntity.getBody();
    }

    public void logout(String accessToken) {
        String apiUrl = props.getApiUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                apiUrl + "/api/logout",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
    }



    public Iterable<Article> getAllArticles() {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Iterable<Article>> responseEntity = restTemplate.exchange(
                apiUrl + "/articles",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Article>>() {}
        );

        return responseEntity.getBody();
    }

    public Article getArticle(int id) {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Article> responseEntity = restTemplate.exchange(
                apiUrl + "/article/" + id,
                HttpMethod.GET,
                null,
                Article.class
        );

        return responseEntity.getBody();
    }

    public Iterable<Article> getArticlesByCategory(Category category) {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Iterable<Article>> responseEntity = restTemplate.exchange(
                apiUrl + "/categorie/" + category.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Article>>() {}
        );

        return responseEntity.getBody();
    }

    public Article createArticle(Article article) {
        String apiUrl = props.getApiUrl();

        HttpEntity<Article> requestEntity = new HttpEntity<>(article);

        ResponseEntity<Article> responseEntity = restTemplate.exchange(
                apiUrl + "/article/create",
                HttpMethod.POST,
                requestEntity,
                Article.class
        );

        return responseEntity.getBody();
    }

    public Article updateArticle(Article article) {
        String apiUrl = props.getApiUrl();

        HttpEntity<Article> requestEntity = new HttpEntity<>(article);

        ResponseEntity<Article> responseEntity = restTemplate.exchange(
                apiUrl + "/article/update/" + article.getId(),
                HttpMethod.PUT,
                requestEntity,
                Article.class
        );

        return responseEntity.getBody();
    }

    public void deleteArticle(long id) {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                apiUrl + "/article/delete/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    public Iterable<Category> getAllCategories() {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Iterable<Category>> responseEntity = restTemplate.exchange(
                apiUrl + "/categories",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Category>>() {}
        );

        return responseEntity.getBody();
    }

    public Category getCategory(int id) {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Category> responseEntity = restTemplate.exchange(
                apiUrl + "/category/" + id,
                HttpMethod.GET,
                null,
                Category.class
        );

        return responseEntity.getBody();
    }

    public Category createCategory(Category category) {
        String apiUrl = props.getApiUrl();

        HttpEntity<Category> requestEntity = new HttpEntity<>(category);

        ResponseEntity<Category> responseEntity = restTemplate.exchange(
                apiUrl + "/category/create",
                HttpMethod.POST,
                requestEntity,
                Category.class
        );

        return responseEntity.getBody();
    }

    public Category updateCategory(Category category) {
        String apiUrl = props.getApiUrl();

        HttpEntity<Category> requestEntity = new HttpEntity<>(category);

        ResponseEntity<Category> responseEntity = restTemplate.exchange(
                apiUrl + "/category/update/" + category.getId(),
                HttpMethod.PUT,
                requestEntity,
                Category.class
        );

        return responseEntity.getBody();
    }

    public void deleteCategory(long id) {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                apiUrl + "/category/delete/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}
