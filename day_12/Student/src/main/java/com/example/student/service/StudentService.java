package com.example.student.service;

import com.example.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // Tạo danh sách sinh viên để quản lý
    private List<Student> students;

    // Khi class được khởi tạo thì chạy method để thêm sinh viên vào list
    public StudentService() {
        init();
    }

    // Thêm 1 số đối tượng student vào list để quản lý
    public void init() {
        students = new ArrayList<>();
        students.add(new Student(1, "Nguyễn Văn A", "a@gmail.com"));
        students.add(new Student(2, "Trần Văn B", "b@gmail.com"));
        students.add(new Student(3, "Ngô Thị C", "c@gmail.com"));
        students.add(new Student(4, "Dương Thị D", "d@gmail.com"));
    }

    // Lấy danh sách học viên
    public List<Student> getStudents() {
        return students;
    }
}
