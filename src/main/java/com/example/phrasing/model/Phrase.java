package com.example.phrasing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "phrases")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private String text;

    @CreatedDate
    @Column(nullable = false, updatable = false, name = "created_at", columnDefinition = "DATETIME")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @OneToMany(mappedBy = "phrase")
    private List<Upvote> upvotes;

}
