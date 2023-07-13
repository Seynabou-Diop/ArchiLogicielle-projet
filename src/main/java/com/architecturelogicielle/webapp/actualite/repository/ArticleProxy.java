package com.architecturelogicielle.webapp.actualite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.architecturelogicielle.webapp.actualite.CustomProperties;
import com.architecturelogicielle.webapp.actualite.model.Article;
import com.architecturelogicielle.webapp.actualite.model.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArticleProxy {

	@Autowired
	private CustomProperties props;

	/**
	 * Get all articles
	 * 
	 * @return An iterable of all articles
	 */
	public Iterable<Article> getAllArticles() {

		String baseApiUrl = props.getApiUrl();
		String getArticlesUrl = baseApiUrl + "/articles";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Article>> response = restTemplate.exchange(
				getArticlesUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Article>>() {
				});

		log.debug("Get Articles call " + response.getStatusCode().toString());

		return response.getBody();
	}

	/**
	 * Get an Article by the id
	 * 
	 * @param id The id of the Article
	 * @return The Article which matches the id
	 */
	public Article getArticle(int id) {
		String baseApiUrl = props.getApiUrl();
		String getArticleUrl = baseApiUrl + "/article/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Article> response = restTemplate.exchange(
				getArticleUrl,
				HttpMethod.GET,
				null,
				Article.class);

		log.debug("Get Article call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	public Iterable<Article> getArticlesByCategory(Category category) {
	    String baseApiUrl = props.getApiUrl();
	    String getArticlesByCategoryUrl = baseApiUrl + "/categorie/" + category.getId();

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Iterable<Article>> response = restTemplate.exchange(
	            getArticlesByCategoryUrl,
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<Iterable<Article>>() {});

	    log.debug("Get Articles by Category call " + response.getStatusCode().toString());

	    return response.getBody();
	}


	public Article createArticle(Article a) {
		String baseApiUrl = props.getApiUrl();
		String createArticleUrl = baseApiUrl + "/article/create";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Article> request = new HttpEntity<Article>(a);
		ResponseEntity<Article> response = restTemplate.exchange(
				createArticleUrl,
				HttpMethod.POST,
				request,
				Article.class);

		log.debug("Create Article call " + response.getStatusCode().toString());

		return response.getBody();
	}

	public Article updateArticle(Article a) {
		String baseApiUrl = props.getApiUrl();
		String updateArticleUrl = baseApiUrl + "/article/update/" + a.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Article> request = new HttpEntity<Article>(a);
		ResponseEntity<Article> response = restTemplate.exchange(
				updateArticleUrl,
				HttpMethod.PUT,
				request,
				Article.class);

		log.debug("Update Article call " + response.getStatusCode().toString());

		return response.getBody();
	}


	public void deleteArticle(long id) {
		String baseApiUrl = props.getApiUrl();
		String deleteArticleUrl = baseApiUrl + "/article/delete" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteArticleUrl,
				HttpMethod.DELETE,
				null,
				Void.class);

		log.debug("Delete Article call " + response.getStatusCode().toString());
	}

}