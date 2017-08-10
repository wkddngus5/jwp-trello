package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Naver on 2017. 6. 29..
 */

@NoArgsConstructor
@ToString
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cardId")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name="deckId")
    private Long deckId;

    @Column(name="contents", nullable = false)
    @Getter
    @Setter
    private String contents;

    public Card(String contents) {
        this.contents = contents;
    }
}
