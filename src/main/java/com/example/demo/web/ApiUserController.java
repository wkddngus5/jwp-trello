package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Naver on 2017. 6. 29..
 */
@RestController
@Slf4j
public class ApiUserController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        log.debug("USER: {}", user);

        return userRepository.save(user);
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession httpSession) {
        log.debug("inputInfo: {}", user);
        User findUser = userRepository.findByUserId(user.getUserId());
        if(findUser == null) {
            return new ResponseEntity<String>("NO ID", HttpStatus.UNAUTHORIZED);
        }
        if(!findUser.samePassword(user)) {
            return new ResponseEntity<String>("DIFFRENT PASSWORD", HttpStatus.BAD_REQUEST);
        }
        httpSession.setAttribute("user", findUser);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
