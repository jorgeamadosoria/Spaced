package org.jasr.spaced.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Card extends BaseEntity {
	@Column(unique=true)
	private String task;
	private String answer;
	@Temporal(TemporalType.DATE)
	private Date play;
	private boolean success;
	@ManyToOne
	private CardSet cardset;

	public Date getPlay() {
		return play;
	}

	public void setPlay(Date play) {
		this.play = play;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
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
