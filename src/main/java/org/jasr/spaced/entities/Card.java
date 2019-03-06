package org.jasr.spaced.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Card extends BaseEntity {
	private String task;
	private String answer;
	@ManyToOne
	private Deck deck;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

}
