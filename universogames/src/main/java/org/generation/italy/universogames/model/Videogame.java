package org.generation.italy.universogames.model;

public class Videogame extends Entity{
	
	private String title;
	private String img;
	private int pegi;
	private String platform;
	private String genre;
	private String pubDate;
	private String description;
	private double sumVotes;
	private int numberVotes;
	
	public Videogame(int id, String title, String img, int pegi, String platform, String genre, String pubDate,
			String description, double sumVotes, int numberVotes) {
		super(id);
		this.title = title;
		this.img = img;
		this.pegi = pegi;
		this.platform = platform;
		this.genre = genre;
		this.pubDate = pubDate;
		this.description = description;
		this.sumVotes = sumVotes;
		this.numberVotes = numberVotes;
	}
	public Videogame() {
		super();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getPegi() {
		return pegi;
	}
	public void setPegi(int pegi) {
		this.pegi = pegi;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public double getSumVotes() {
		return sumVotes;
	}
	public void setSumVotes(double sumVotes) {
		this.sumVotes = sumVotes;
	}
	public int getNumberVotes() {
		return numberVotes;
	}
	public void setNumberVotes(int numberVotes) {
		this.numberVotes = numberVotes;
	}
	@Override
	public String toString() {
		return "{title:" + title + ", img:" + img + ", pegi:" + pegi + ", platform:" + platform + ", genre:" + genre
				+ ", pubDate:" + pubDate + ", description:" + description + ", sumVotes:" + sumVotes + ", numberVotes:"
				+ numberVotes + ", toString():" + super.toString() + "}\n";
	}

}