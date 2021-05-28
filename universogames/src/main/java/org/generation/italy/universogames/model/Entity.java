package org.generation.italy.universogames.model;

import org.generation.italy.universogames.util.IMappablePro;

public class Entity implements IMappablePro{
	
	private int id;

	public Entity(int id) {
		super();
		this.id = id;
	}
	
	public Entity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
