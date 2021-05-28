package org.generation.italy.universogames.dao;

import java.util.List;

import org.generation.italy.universogames.model.News;
import org.generation.italy.universogames.model.Review;

public interface IDaoPosts {
		
	 	List<News> news();
	    
	    List<News> newsWithoutReviews();
	    
	    List<News> newsWithoutReviews(int num, int page);
	    
	    List<News> newsWithoutReviewsRandom(int num);

	    List<Review> reviews();
	    
	    List<Review> reviews(int num, int page);

	    List<News> newsPerTag(int num, String tag);   
	    
	    News news(int id);

	    Review review(int id);
	    
	    Review reviewVG(int idVideogame);
	    
	    void addImgCarousel(String nomeFile, int idReview);
	    
	    int addNews(News news);
	    
	    void addTag(String name, int idNews);

	    int addReview(Review review);
	    
	    void deleteNews(int id);

	    void deleteReview(int id);

	    void updateNews(News news);

	    void updateReview(Review review);
}
