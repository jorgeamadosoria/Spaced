package org.jasr.spaced.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class CardSet extends BaseEntity {

	private String name;
	private String image;
	@Column(columnDefinition="TEXT")
	private String description;
	@OneToMany
	@JoinColumn(name = "cardset_id")
	@Cascade({CascadeType.ALL})
	private List<Card> cards;
	
    public CardSet() {
		super();
	}

	public CardSet(Long id) {
		super(id);
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
/*
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result + ((decks == null) ? 0 : decks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && this.getId().equals(((CardSet) obj).getId());
	}
*/
}
