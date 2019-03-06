package org.jasr.spaced.repositories;

import org.jasr.spaced.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {

}
