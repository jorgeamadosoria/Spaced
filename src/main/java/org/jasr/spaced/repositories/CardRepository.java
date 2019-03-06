package org.jasr.spaced.repositories;

import org.jasr.spaced.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
