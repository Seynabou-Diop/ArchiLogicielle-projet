package com.architecturelogicielle.webapp.actualite.repository;

import com.architecturelogicielle.webapp.actualite.CustomProperties;
import com.architecturelogicielle.webapp.actualite.auth.AuthenticationResponse;
import com.architecturelogicielle.webapp.actualite.controller.AuthenticationRequest;
import com.architecturelogicielle.webapp.actualite.model.Article;
import com.architecturelogicielle.webapp.actualite.model.Category;
import com.architecturelogicielle.webapp.actualite.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserProxy {
    @Autowired
    private CustomProperties props;

    @Autowired
    private RestTemplate restTemplate;

    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        String baseApiUrl = props.getApiUrl();
        String getAuthUrl = baseApiUrl + "/api/auth/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authenticationRequest, headers);

        ResponseEntity<AuthenticationResponse> responseEntity = restTemplate.exchange(
                getAuthUrl,
                HttpMethod.POST,
                requestEntity,
                AuthenticationResponse.class
        );

        return responseEntity.getBody();
    }

    public List<String> getUserRoles(String accessToken) {
        String apiUrl = props.getApiUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(
                apiUrl + "/api/user/role",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<String>>() {}
        );

        return responseEntity.getBody();
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


    public List<Utilisateur> getAllUtilisateurs() {
        String apiUrl = props.getApiUrl();

        ResponseEntity<List<Utilisateur>> responseEntity = restTemplate.exchange(
                apiUrl + "/utilisateurs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Utilisateur>>() {}
        );

        return responseEntity.getBody();
    }

    public Utilisateur getUtilisateur(int id) {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Utilisateur> responseEntity = restTemplate.exchange(
                apiUrl + "/utilisateur/" + id,
                HttpMethod.GET,
                null,
                Utilisateur.class
        );

        return responseEntity.getBody();
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        String apiUrl = props.getApiUrl();

        HttpEntity<Utilisateur> requestEntity = new HttpEntity<>(utilisateur);

        ResponseEntity<Utilisateur> responseEntity = restTemplate.exchange(
                apiUrl + "/user/create",
                HttpMethod.POST,
                requestEntity,
                Utilisateur.class
        );

        return responseEntity.getBody();
    }

    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        String apiUrl = props.getApiUrl();

        HttpEntity<Utilisateur> requestEntity = new HttpEntity<>(utilisateur);

        ResponseEntity<Utilisateur> responseEntity = restTemplate.exchange(
                apiUrl + "/utilisateur/update/" + utilisateur.getId(),
                HttpMethod.PUT,
                requestEntity,
                Utilisateur.class
        );

        return responseEntity.getBody();
    }

    public void deleteUtilisateur(int id) {
        String apiUrl = props.getApiUrl();

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                apiUrl + "/utilisateur/delete/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}

