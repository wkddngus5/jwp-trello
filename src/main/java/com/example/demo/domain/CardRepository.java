package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Naver on 2017. 7. 4..
 */
public interface CardRepository extends CrudRepository<Card, Long> {
    Card findByid(Long id);
    Set<Card> findByDeckId(Long deckId);
}
