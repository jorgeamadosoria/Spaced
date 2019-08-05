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
	int updateCardDate(@Param("success") Date success, @Param("play") Date play, @Param("id") Long id);

	@Modifying
	@Query("update Card c set c.play = :play where c.id = :id")
	int updateCardDate(@Param("play") Date play, @Param("id") Long id);
	
	Page<Card> findAllByCardsetId(Long cardsetId,Pageable pageable);
	
	public List<Card> findTop5ByCardsetIdAndPlayIsNull(Long id);
	public List<Card> findTop10ByCardsetIdAndRecurrenceGreaterThanAndPlayIsNotAndPlayIsNotNullOrderByPlayAsc(Long id,int recurrence, Date now);
}
