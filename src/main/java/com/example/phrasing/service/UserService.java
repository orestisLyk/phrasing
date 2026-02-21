package com.example.phrasing.service;

import com.example.phrasing.core.exceptions.EmailAlreadyExistsException;
import com.example.phrasing.core.exceptions.UsernameAlreadyExistsException;
import com.example.phrasing.dto.UserInsertDTO;
import com.example.phrasing.dto.UserReadOnlyDTO;
import com.example.phrasing.mapper.Mapper;
import com.example.phrasing.model.User;
import com.example.phrasing.repository.PhraseRepository;
import com.example.phrasing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PhraseRepository phraseRepository;

    @Transactional(rollbackFor = {UsernameAlreadyExistsException.class, EmailAlreadyExistsException.class})
    public UserReadOnlyDTO register(UserInsertDTO dto) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(dto.username());
        }
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailAlreadyExistsException(dto.email());
        }

        userRepository.save(user);
        return Mapper.mapToUserReadOnlyDTO(user);
    }
}
