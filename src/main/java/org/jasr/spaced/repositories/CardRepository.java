package org.jasr.spaced.repositories;

import java.util.Date;
import java.util.List;

import org.jasr.spaced.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CardRepository extends JpaRepository<Card, Long> {
	@Modifying
	@Query("update Card c set c.play = :play, c.success = :success where c.id = :id")
	int updateCardDate(@Param("success") Boolean success, @Param("play") Date play, @Param("id") Long id);

	Page<Card> findAllByCardsetIdOrderByTaskAsc(Long cardsetId,Pageable pageable);
	//cards not tried yet
	public List<Card> findTop10ByCardsetIdAndSuccessIsNull(Long id);
	//cards unsuccessfully tried
	public List<Card> findTop10ByCardsetIdAndSucessIsFalseOrderByPlayAsc(Long id);
	//cards successfully tried
	public List<Card> findTop10ByCardsetIdAndSucessIsTrueOrderByPlayAsc(Long id);
}
