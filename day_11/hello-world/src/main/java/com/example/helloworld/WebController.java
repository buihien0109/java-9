package com.example.helloworld;

// Controller + ResponseBody = RestController

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebController {
    private List<User> users = new ArrayList<>();

    public WebController() {
        users.add(new User(1, "Nguyễn Văn A"));
        users.add(new User(2, "Trần Văn B"));
        users.add(new User(3, "Ngô Thị C"));
    }

    // API = Http Method + URL
    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    public List<User> getUsers() {
        return users;
    }

    // users/1, users/10
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // users/search?name=van
    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
    public List<User> searchUser(@RequestParam String name) {
        return users
                .stream()
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());
    }
}
