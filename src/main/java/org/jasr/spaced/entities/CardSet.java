package org.jasr.spaced.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class CardSet extends BaseEntity {

	private String name;
	private String image;
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

}
