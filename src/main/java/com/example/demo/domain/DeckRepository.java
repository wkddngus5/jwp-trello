package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Naver on 2017. 7. 4..
 */
public interface DeckRepository extends CrudRepository<Deck, Long> {
    Deck findByid(Long id);
    Set<Deck> findByUserId(String userId);
}
