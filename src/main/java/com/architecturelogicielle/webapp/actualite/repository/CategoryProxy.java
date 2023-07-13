package com.architecturelogicielle.webapp.actualite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.architecturelogicielle.webapp.actualite.CustomProperties;
import com.architecturelogicielle.webapp.actualite.model.Category;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Component
public class  CategoryProxy {

    @Autowired
    private CustomProperties props;

    @Autowired
    private RestTemplate restTemplate;

    public List<Category> getAllCategories() {
        String baseApiUrl = props.getApiUrl();
        String getCategoriesUrl = baseApiUrl + "/categories";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Category>> response = restTemplate.exchange(
                getCategoriesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Category>>() {});

        log.debug("Get Categories call " + response.getStatusCode().toString());

        return response.getBody();
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
