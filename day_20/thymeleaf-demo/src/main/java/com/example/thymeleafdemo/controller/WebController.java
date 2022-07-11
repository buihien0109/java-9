package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.model.User;
import com.example.thymeleafdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("user", userService.getUserById(1));
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("age", 17);
        model.addAttribute("day", 7);
        model.addAttribute("user", userService.getUserById(2));
        model.addAttribute("users", userService.getUsers());
        return "about";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("user", userService.getUserById(3));
        model.addAttribute("users", userService.getUsers());
//        model.addAttribute("users", new ArrayList<>());

        model.addAttribute("message", "xin chào các bạn");

        Date date = new Date();
        model.addAttribute("date", date);

        LocalDate localDate = LocalDate.now();
        model.addAttribute("localDate", localDate);

        model.addAttribute("byId", Comparator.comparing(User::getId).reversed());

        model.addAttribute("price", 20_000_000);

        model.addAttribute("number", 10_000_000.1);

        model.addAttribute("a", 10);
        model.addAttribute("b", 20);
        return "contact";
    }

    @GetMapping("/fragment")
    public String getContactPage() {
        return "fragment";
    }
}
