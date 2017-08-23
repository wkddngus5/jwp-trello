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
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="boardId")
    @Getter
    @Setter
    private Long id;

    @Column(name="title", nullable = false, unique = true)
    @Getter
    @Setter
    private String title;

    @OneToMany
    @JoinColumn(name = "boardId")
    @Getter
    @Setter
    private Set<Deck> decks;

    public static Board searchSameBoard(Set<Board> boards, Board objectBoard) {
        for(Board board : boards) {
            if(board.getTitle().equals(objectBoard.getTitle())) {
                return board;
            }
        }
        return null;
    }
}
