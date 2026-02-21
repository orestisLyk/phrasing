package com.example.phrasing.service;

import com.example.phrasing.dto.PhraseInsertDTO;
import com.example.phrasing.mapper.Mapper;
import com.example.phrasing.model.Phrase;
import com.example.phrasing.model.User;
import com.example.phrasing.repository.PhraseRepository;
import com.example.phrasing.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhraseService {

    private final PhraseRepository phraseRepository;
    private final UserRepository userRepository;

    public void createPhrase(PhraseInsertDTO dto) {
        Phrase phrase = Mapper.mapToPhraseEntity(dto);
        phrase.setUuid(UUID.randomUUID());
        phrase.setUser(userRepository.findByUsername(dto.username())
                .orElseThrow(() ->  new EntityNotFoundException("User not found")));
        phraseRepository.save(phrase);
    }

    public Page<Phrase> getMostPopular(Pageable pageable) {
        return phraseRepository.findAllOrderByUpvoteCountDesc(pageable);
    }

    public Page<Phrase> getLatest(Pageable pageable) {
        return phraseRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    public Page<Phrase> getAllByUser(Long userId, Pageable pageable) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return phraseRepository.findByUser(user,  pageable);
    }
}
