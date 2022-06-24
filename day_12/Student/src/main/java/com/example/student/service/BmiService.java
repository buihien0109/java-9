package com.example.student.service;

import com.example.student.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    public double getBmi(double height, double weight) {
        // Kiểm tra weight hoặc height <= 0 --> BadRequestException
        if (weight <= 0 || height <= 0) {
            throw new BadRequestException("Cân nặng hoặc chiều cao phải >= 0");
        }
        return weight / (height * height);
    }
}
