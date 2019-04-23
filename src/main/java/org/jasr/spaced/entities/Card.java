package org.jasr.spaced.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class Card extends BaseEntity {
	private String task;
	private String answer;
	@Temporal(TemporalType.DATE)
	private Date play;
	@Temporal(TemporalType.DATE)
	private Date success;
	@ColumnDefault("0")
	private int recurrence;
	@ManyToOne
	private CardSet cardset;

	private static int[] RECURRENCES = new int[] { 0, 1, 2, 7, 7, 15, 30, 90, -1 };

	public void changeRecurrence() {
		recurrence = Objects.equals(this.play, this.success) ? Math.min(recurrence + 1, RECURRENCES.length - 1) : RECURRENCES[1];
	}

	public int getRecurrenceDays() {
		return RECURRENCES[this.recurrence];
	}
	
	public int getRecurrence() {
		return recurrence;
	}



	public void setRecurrence(int recurrence) {
		this.recurrence = recurrence;
	}



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
