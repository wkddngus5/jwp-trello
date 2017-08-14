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
public class ApiCommentController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "commentRepository")
    private CommentRepository commentRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/api/comments/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Comment> create(HttpSession httpSession, @PathVariable Long cardId) {
        Set<Comment> comments = commentRepository.findByCardId(cardId);
        log.debug("finded comments: {}", comments);
        return comments;
    }

    @PostMapping("/api/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(HttpSession httpSession, @RequestBody Comment comment) {
        commentRepository.save(comment);
        return comment;
    }
}
