package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Naver on 2017. 6. 29..
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
