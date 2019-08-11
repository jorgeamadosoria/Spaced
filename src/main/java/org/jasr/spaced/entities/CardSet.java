package org.jasr.spaced.entities;

import javax.persistence.Entity;

@Entity
public class CardSet extends BaseEntity {

	private String name;
	private String description;
	private String image;

	public CardSet() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CardSet(Long id) {
		super(id);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
