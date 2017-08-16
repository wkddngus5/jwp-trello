package com.example.demo.web;

import com.example.demo.domain.DeckRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Naver on 2017. 6. 29..
 */

@Slf4j
@Controller
public class HomeController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "deckRepository")
    private DeckRepository deckRepository;

    @GetMapping("/")
    public String home(HttpSession httpSession) {
        log.debug("session: {}", httpSession);
//        User sessionedUser = (User)httpSession.getAttribute("user");
//        log.debug("session user: {}", httpSession);
//        if(sessionedUser != null) {
//            return "redirect:board";
//        }
        return "index";
    }

    @GetMapping("/loginForm")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "login";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/board")
    public String board(Model model) {
        log.debug("DECKS: {}", deckRepository.findAll());
        model.addAttribute("decks", deckRepository.findAll());
        return "board";
    }

    @GetMapping("/boards")
    public String boards(Model model, HttpSession httpSession) {
        User sessionedUser = (User)httpSession.getAttribute("user");
        if(sessionedUser == null) {
            return "login";
        }
        model.addAttribute("decks", userRepository.findByUserId(sessionedUser.getUserId()).getDecks());
        return "boards";
    }
}
