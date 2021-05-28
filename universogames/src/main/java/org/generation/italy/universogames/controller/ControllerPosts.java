package org.generation.italy.universogames.controller;


import java.util.List;

import org.generation.italy.universogames.dao.IDaoPosts;
import org.generation.italy.universogames.model.News;
import org.generation.italy.universogames.model.Review;
import org.generation.italy.universogames.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/posts")
public class ControllerPosts {
	
	@Autowired
	private FileStorageService fs;
	
	@Autowired
	private IDaoPosts dao;
	
	@PostMapping("/img/upload")
	public String uploadFile(@RequestParam MultipartFile file) {
		if(!file.isEmpty()) {
			return fs.saveFile(file);
		}
		return "Non hai inserito il file.";
	}
	
	/**
	 * Aggiunta immagine di carosello alla review con id = idReview
	 * @param file
	 * @param idReview
	 * @return
	 */
	@PostMapping("/imgcarousel/upload")
	public String uploadFile(@RequestParam MultipartFile file, @PathVariable int idReview) {
		if(!file.isEmpty()) {
			String nomeFile = fs.saveFile(file);
			dao.addImgCarousel(nomeFile, idReview);
			return nomeFile + " aggiunto.";
		}
		return "Non hai inserito il file.";
	}
	
	@DeleteMapping("/img/delete/{fileName}")
	public void deleteFile(@PathVariable String fileName) {
		fs.deleteFile(fileName);
	}
	 
	@GetMapping("/news")
	public List<News> getNews() {
		return dao.newsWithoutReviews();
	}
	
	@GetMapping("/reviews")
	public List<Review> getReviews() {
		return dao.reviews();
	}
	
	@GetMapping("/list/news/{num}/{page}")
	public List<News> getNews(@PathVariable int num, @PathVariable int page) {
		return dao.newsWithoutReviews(num,page);
	}
	
	@GetMapping("/list/reviews/{num}/{page}")
	public List<Review> getReviews(@PathVariable int num, @PathVariable int page) {
		return dao.reviews(num,page);
	}
	
	@GetMapping("/list/randomnews/{num}")
	public List<News> getNewsRandom(@PathVariable int num) {
		return dao.newsWithoutReviewsRandom(num);
	}
	
	@GetMapping("/list/newsPerTag/{num}/{tag}")
	public List<News> getNewsPerTag(@PathVariable int num, @PathVariable String tag){
		return dao.newsPerTag(num, tag);
	}
	
	@GetMapping("/news/{id}")
	public News getOneNews(@PathVariable int id) {
		return dao.news(id);
	}
	
	@GetMapping("/reviews/{id}")
	public Review getOneReview(@PathVariable int id) {
		return dao.review(id);
	}
	
	@GetMapping("/reviewVG/{idVideogame}")
	public Review getReviewVG(@PathVariable int idVideogame) {
		return dao.reviewVG(idVideogame);
	}
	
	@PostMapping("/news")
	public int addNews(@RequestBody News news) {
		return dao.addNews(news);
	}
	
	@PostMapping("/tag/{name}/{idNews}")
	public void addTag(@PathVariable String name, @PathVariable int idNews) {
		dao.addTag(name, idNews);
	}
	
	@PostMapping("/reviews")
	public int addReview(@RequestBody Review review) {
		return dao.addReview(review);
	}
	
	@DeleteMapping("/news/{id}")
	public void deleteNews(@PathVariable int id) {
		dao.deleteNews(id);
	}
	
	@DeleteMapping("/reviews/{id}")
	public void deleteReview(@PathVariable int id) {
		dao.deleteNews(id);
	}
	
	@PutMapping("/news")
	public void updateNews(@RequestBody News news) {
		dao.updateNews(news);
	}
	
	@PutMapping("/reviews")
	public void updateReview(@RequestBody Review review) {
		dao.updateReview(review);
	}
	
	
}
