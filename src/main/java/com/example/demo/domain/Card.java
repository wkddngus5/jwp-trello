package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "cardId")
    @Getter
    @Setter
    private Set<Comment> comments;

    public Card(String contents) {
        this.contents = contents;
    }

    public Card(Long id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
