package com.example.phrasing.repository;

import com.example.phrasing.model.Phrase;
import com.example.phrasing.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {


    @Query(
            value = """
               SELECT p
               FROM Phrase p
               LEFT JOIN Upvote u ON u.phrase = p
               GROUP BY p
               ORDER BY COUNT(u.id) DESC
               """,
            countQuery = "SELECT COUNT(p) FROM Phrase p"
    )
    Page<Phrase> findAllOrderByUpvoteCountDesc(Pageable pageable);

    Page<Phrase> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Phrase> findByUser(User user, Pageable pageable);


}
