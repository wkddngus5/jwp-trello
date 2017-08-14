package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Naver on 2017. 7. 4..
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findByid(Long id);
    Set<Comment> findByCardId(Long cardId);
}
