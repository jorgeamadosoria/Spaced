package org.jasr.spaced.entities;

public enum Decks {

	UNDECKED(0), CURRENT(1), OTHER_DAY(2), WEEKLY(7), MONTHLY(30), RETIRED(-1);

	int days;

	Decks(int days) {
		this.days = days;
	}

}
