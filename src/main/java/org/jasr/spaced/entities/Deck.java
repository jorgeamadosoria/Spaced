package org.jasr.spaced.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Deck extends BaseEntity{
    private String name;
    private Integer repetitionDays;
    private String color;
    
    @OneToMany
    private Set<Card> cards;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRepetitionDays() {
		return repetitionDays;
	}

	public void setRepetitionDays(Integer repetitionDays) {
		this.repetitionDays = repetitionDays;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}


}
