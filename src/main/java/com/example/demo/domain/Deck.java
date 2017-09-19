package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Naver on 2017. 6. 29..
 */

@NoArgsConstructor
@ToString
@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="deckId")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name="boardId")
    private Long boardId;

    @Column(name="title", nullable = false)
    @Getter
    @Setter
    private String title;

    @OneToMany
    @JoinColumn(name = "deckId")
    @Getter
    @Setter
    private Set<Card> cards;

    public Deck(String title) {
        this.title = title;
    }

}
