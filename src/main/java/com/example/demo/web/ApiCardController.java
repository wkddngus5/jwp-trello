package com.example.demo.web;

import com.example.demo.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by Naver on 2017. 6. 29..
 */
@RestController
@Slf4j
public class ApiCardController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "cardRepository")
    private CardRepository cardRepository;


    @PostMapping("/api/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public Card create(HttpSession httpSession, @RequestBody Card card) {
        log.debug("input card: {}", card);
        return cardRepository.save(card);
    }

    @PutMapping("/api/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public Card edit(HttpSession httpSession, @RequestBody Card card) {
        log.debug("input card: {}", card);
        Card findedCard = cardRepository.findByid(card.getId());
        log.debug("CARD: {}", card);
        findedCard.setContents(card.getContents());
        cardRepository.save(findedCard);
        return findedCard;
    }
}
