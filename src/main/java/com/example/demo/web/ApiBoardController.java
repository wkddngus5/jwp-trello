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
@RequestMapping("/api/boards")
public class ApiBoardController {
    @Resource(name = "boardRepository")
    private BoardRepository boardRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(Principal principal, @RequestBody Board board) {
        User sessionUser = userRepository.findByEmail(principal.getName());

        log.debug("session user: {}", sessionUser);
        sessionUser.addBoard(board);
        log.debug("input board: {}", board);
        Set<Board> boards = userRepository.save(sessionUser).getBoards();
        return "api/boards/" + Board.searchSameBoard(boards, board).getId();
    }

    @GetMapping("/{id}")
    public Board getBoard(@PathVariable long id) {
        return boardRepository.findByid(id);
    }

    //return responseEntity
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(Principal principal, @PathVariable long id) {
        User sessionUser = userRepository.findByEmail(principal.getName());
        Board toDeleteBoard = boardRepository.findByid(id);
        if(sessionUser.hasBoard(toDeleteBoard)) {
            log.debug("{} deleted", toDeleteBoard);
            boardRepository.delete(id);
        } else {
            new IllegalArgumentException();
            log.debug("can't delete this: {}", toDeleteBoard);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public String editBoard(Principal principal, @PathVariable long id, @RequestBody Board inputBoard) {
        User sessionUser = userRepository.findByEmail(principal.getName());
        Board toEditBoard = boardRepository.findByid(id);
        if(sessionUser.hasBoard(toEditBoard)) {
            log.debug("{} edited", toEditBoard);
            return "api/boards/" + boardRepository.save(toEditBoard).getId();
        } else {
            return "you can't edit this board";
        }
    }

    //@ExceptionHandler controller advice
}
