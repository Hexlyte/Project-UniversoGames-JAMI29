package org.generation.italy.universogames.model;

import org.generation.italy.universogames.util.IMappablePro;

public class RankedVG implements IMappablePro{
	
	private int idVideogame;
	private String title;
	private double score;
	
	
	public RankedVG(int idVideogame, String title, double score) {
		super();
		this.idVideogame = idVideogame;
		this.title = title;
		this.score = score;
	}

	public RankedVG() {
		super();
	}

	public int getIdVideogame() {
		return idVideogame;
	}
	public void setIdVideogame(int idVideogame) {
		this.idVideogame = idVideogame;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "{idVideogame:" + idVideogame + ", title:" + title + ", score:" + score + "}\n";
	}
	                                                  
	
}
