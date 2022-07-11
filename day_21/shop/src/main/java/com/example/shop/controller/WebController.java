package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/shop")
    public String getShopPage() {
        return "shop";
    }

    @GetMapping("/buy")
    public String getBuyPage() {
        return "buy";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }
}
