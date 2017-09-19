package com.example.demo.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Naver on 2017. 8. 24..
 */
@Slf4j
public class UserTest {

    @Test
    public void hasUser() {
        User user = new User();
        Board board = new Board();

        user.addBoard(board);
    }
}
