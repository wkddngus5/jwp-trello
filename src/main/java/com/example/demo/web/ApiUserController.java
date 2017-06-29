package com.example.demo.web;

import com.example.demo.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naver on 2017. 6. 29..
 */
@RestController
public class ApiUserController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody User user) {
        log.debug("USER: {}", user);

        return "index";
    }
}
