package com.example.demo.web;

import com.example.demo.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Naver on 2017. 6. 29..
 */
@RestController
@Slf4j
public class ApiUserController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "roleRepository")
    private RoleRepository roleRepository;

    @Resource(name = "boardRepository")
    private BoardRepository boardRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        user.serializePassword(bCryptPasswordEncoder);
        Role defaultRole = roleRepository.findByRole("USER");
        user.addRole(defaultRole);
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> geAlltUsers(HttpSession httpSession) {
        return userRepository.findAll();
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession httpSession) {
        log.debug("inputInfo: {}", user);
        User findUser = userRepository.findById(user.getId());
        if (findUser == null) {
            return new ResponseEntity<String>("There is no account for this id", HttpStatus.UNAUTHORIZED);
        }

        if (!bCryptPasswordEncoder.matches(user.getPassword(), findUser.getPassword())) {
            return new ResponseEntity<String>("Password does not match the confirm password", HttpStatus.BAD_REQUEST);
        }
        httpSession.setAttribute("user", findUser);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/api/users/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<User> getBoardUsers(Principal principal, @PathVariable Long boardId) {
        if (principal == null) {
            return null;
        }

        Set<User> users = new HashSet<>();
        for (User user : userRepository.findAll()) {
            if (user.hasBoard(boardRepository.findByid(boardId))) {
                users.add(user);
            }
        }

        log.debug("finded users: {}", users);
        return users;
    }
}
