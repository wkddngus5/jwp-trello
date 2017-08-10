package com.example.demo.web;

import com.example.demo.domain.Deck;
import com.example.demo.domain.DeckRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Naver on 2017. 6. 29..
 */
@RestController
@Slf4j
public class ApiDeckController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "deckRepository")
    private DeckRepository deckRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/api/decks")
    @ResponseStatus(HttpStatus.CREATED)
    public Deck create(HttpSession httpSession, @RequestBody Deck deck) {
        User sessionedUser = (User)httpSession.getAttribute("user");
        if(sessionedUser == null) {
            return null;
        }
        deck.setUserId(sessionedUser.getId());
        deckRepository.save(deck);
        return deck;
    }
}
