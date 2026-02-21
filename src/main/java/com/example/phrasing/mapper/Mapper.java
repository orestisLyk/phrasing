package com.example.phrasing.mapper;

import com.example.phrasing.dto.PhraseInsertDTO;
import com.example.phrasing.dto.UserInsertDTO;
import com.example.phrasing.dto.UserReadOnlyDTO;
import com.example.phrasing.model.Phrase;
import com.example.phrasing.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;


public class Mapper {
    private Mapper() {}

    public static User mapToUserEntity(UserInsertDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }

    public static UserReadOnlyDTO  mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getUsername(), user.getEmail());
    }

    public static Phrase mapToPhraseEntity(PhraseInsertDTO dto) {
        Phrase phrase = new Phrase();
        phrase.setText(dto.text());
        return phrase;
    }
}
