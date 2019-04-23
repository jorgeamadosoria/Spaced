package org.jasr.spaced.repositories;

import java.util.Date;

import org.jasr.spaced.entities.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {

	
}
