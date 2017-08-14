package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Naver on 2017. 6. 29..
 */

@NoArgsConstructor
@ToString
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="commentId")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name="cardId")
    private Long cardId;

    @Column(name="contents", nullable = false)
    @Getter
    @Setter
    private String contents;

    @Column(name="createTime")
    @Getter
    @Setter
    private LocalDateTime createTime;

    public Comment(Long id, Long cardId, String contents) {
        this.id = id;
        this.cardId = cardId;
        this.contents = contents;
        this.createTime = LocalDateTime.now();
    }

    public Comment(Long cardId, String contents) {
        this.cardId = cardId;
        this.contents = contents;
        this.createTime = LocalDateTime.now();
    }
}
