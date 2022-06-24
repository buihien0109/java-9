package com.example.student.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public String getHome() {
        return  "<h1>Home Page</h1>";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJson() {
        return  "<h1>Home Page</h1>";
    }

    @GetMapping(value = "/plain-text", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getPlainText() {
        return "Xin chào";
    }
}
