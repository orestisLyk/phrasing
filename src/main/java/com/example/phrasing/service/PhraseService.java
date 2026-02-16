package com.example.phrasing.service;

import com.example.phrasing.model.Phrase;
import com.example.phrasing.model.User;
import com.example.phrasing.repository.PhraseRepository;
import com.example.phrasing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhraseService {

    private final PhraseRepository phraseRepository;
    private final UserRepository userRepository;

    public Page<Phrase> getMostPopular(Pageable pageable) {
        return phraseRepository.findAllOrderByUpvoteCountDesc(pageable);
    }

    public Page<Phrase> getLatest(Pageable pageable) {
        return phraseRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<Phrase> getAllByUser(Long userId, Pageable pageable) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow();
        }
    }
}
