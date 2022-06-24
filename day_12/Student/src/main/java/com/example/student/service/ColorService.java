package com.example.student.service;

import com.example.student.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ColorService {
    public String randomColor(int type) {
        return switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRgbColor();
            default -> throw new BadRequestException("Type không hợp lệ");
        };
    }

    public String randomColorName() {
        String[] colors = {"red", "green", "blue", "pink", "yellow"};

        Random rd = new Random();
        int index = rd.nextInt(colors.length);
        return colors[index];
    }

    // # [0->9, a->f]
    public String randomHexColor() {
        return null;
    }

    public String randomRgbColor() {
        return null;
    }
}
