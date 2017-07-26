package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Naver on 2017. 7. 25..
 */
@Slf4j
public class PasswordEncoderTest {

    @Test
    public void test1() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "abcdefg";

        String result = passwordEncoder.encode(password);
        String result2 = passwordEncoder.encode(password);

        log.debug("RESULT1: {}", result);
        log.debug("RESULT2: {}", result2);

        log.debug("MATCH: {}", passwordEncoder.matches(password, result));
        log.debug("MATCH2: {}", passwordEncoder.matches(password, result2));
    }
}
