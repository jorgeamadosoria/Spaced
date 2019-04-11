package org.jasr.spaced.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Card extends BaseEntity {
	private String task;
	private String answer;
	private Date play;
	private Date success;
	@ManyToOne
	private CardSet cardset;

	

	public Date getPlay() {
		return play;
	}

	public void setPlay(Date play) {
		this.play = play;
	}

	public Date getSuccess() {
		return success;
	}

	public void setSuccess(Date success) {
		this.success = success;
	}

	public CardSet getCardset() {
		return cardset;
	}

	public void setCardset(CardSet cardset) {
		this.cardset = cardset;
	}

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

}
