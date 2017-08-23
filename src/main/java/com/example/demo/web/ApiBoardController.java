package com.example.demo.web;

import com.example.demo.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Set;

/**
 * Created by Naver on 2017. 6. 29..
 */
@RestController
@Slf4j
public class ApiBoardController {
    @Resource(name = "boardRepository")
    private BoardRepository boardRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @PostMapping("/api/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public Board create(Principal principal, @RequestBody Board board) {
        if (principal == null) {
            return null;
        }

        User sessionUser = userRepository.findByEmail(principal.getName());

        log.debug("session user: {}", sessionUser);
        sessionUser.addBoard(board);
        log.debug("input board: {}", board);
        Set<Board> boards = userRepository.save(sessionUser).getBoards();
        return Board.searchSameBoard(boards, board);
    }
}
