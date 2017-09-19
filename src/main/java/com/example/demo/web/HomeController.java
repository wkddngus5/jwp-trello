package com.example.demo.web;

import com.example.demo.domain.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by Naver on 2017. 6. 29..
 */

@Slf4j
@Controller
public class HomeController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "boardRepository")
    private BoardRepository boardRepository;

    @GetMapping("/")
    public String home(Principal principal) {
        if (principal != null) {
            log.debug("user session: {}", principal.getName());
            return "redirect:boards";
        }
        return "index";
    }

    @GetMapping("/loginForm")
    public String login() {
        return "login";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/boards/{id}")
    public String board(@PathVariable(value = "id") long id, Model model, Principal principal) {
        Board board = boardRepository.findByid(id);
        log.debug("board: {}", board);
        model.addAttribute("board", boardRepository.findByid(id));
        return "board";
    }

    @GetMapping("/boards")
    public String boards(Model model, Principal principal) {
        log.debug("boards: {}", userRepository.findByEmail(principal.getName()).getBoards());
        model.addAttribute("boards",
                userRepository.findByEmail(principal.getName()).getBoards());
        return "boards";
    }
}
