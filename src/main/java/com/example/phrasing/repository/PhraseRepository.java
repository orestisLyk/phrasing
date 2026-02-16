package com.example.phrasing.repository;

import com.example.phrasing.model.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {


    @Query("""
           SELECT p
           FROM Phrase p
           LEFT JOIN p.upvotes u
           GROUP BY p
           ORDER BY COUNT(u) DESC
           """)
    List<Phrase> findAllOrderByUpvoteCountDesc();

    List<Phrase> findAllByOrderByCreatedAtDesc();


}
