package com.example.student.controller;

import com.example.student.request.BmiRequest;
import com.example.student.service.BmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BmiController {
    @Autowired
    private BmiService bmiService;

    @PostMapping("bmi-post")
    public double calculateBmiPost(@RequestBody BmiRequest request) {
        return bmiService.getBmi(request.getHeight(), request.getWeight());
    }

    @GetMapping("/bmi-get")
    public double calculateBmiGet(@RequestParam double height, @RequestParam double weight) {
        return bmiService.getBmi(height, weight);
    }
}
