package org.generation.italy.universogames.model;

import java.util.List;

public class Review extends News {

    private double score;
    private List<String> imgCarousel;
    private int idNews;
    private int idVideogame;
    private String video;

    public Review(int id, String title, String imgCover, String preview, String text, String pubDate, String pubTime,
			String author, double score, List<String> imgCarousel, int idNews, int idVideogame, String video) {
		super(id, title, imgCover, preview, text, pubDate, pubTime, author);
		this.score = score;
		this.imgCarousel = imgCarousel;
		this.idNews = idNews;
		this.idVideogame = idVideogame;
		this.video = video;
	}

	public Review(int id, String title, String imgCover, String preview, String text, String pubDate, String pubTime,
			String author, double score, int idNews, int idVideogame, String video) {
		super(id, title, imgCover, preview, text, pubDate, pubTime, author);
		this.score = score;
		this.idNews = idNews;
		this.idVideogame = idVideogame;
		this.video = video;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public int getIdVideogame() {
		return idVideogame;
	}

	public void setIdVideogame(int idVideogame) {
		this.idVideogame = idVideogame;
	}

	public Review() {
        super();
    }

    public List<String> getImgCarousel() {
		return imgCarousel;
	}

	public void setImgCarousel(List<String> imgCarousel) {
		this.imgCarousel = imgCarousel;
	}

	public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "{score:" + score + ", imgCarousel:" + imgCarousel + ", idNews:" + idNews + ", idVideogame:"
				+ idVideogame + ", video:" + video + ", toString():" + super.toString() + "}\n";
	}

}
