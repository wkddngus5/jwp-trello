package com.example.demo.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping("/infos")
    public Map<String, String> index(HttpServletRequest request) {
        Map<String, String> infos = new HashMap<>();
        infos.put("localAddr", request.getLocalAddr());
        infos.put("localName", request.getLocalName());
        return infos;
    }
}