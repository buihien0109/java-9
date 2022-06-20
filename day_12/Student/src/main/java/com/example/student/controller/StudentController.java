package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //    1. Lấy danh sách sinh viên (GET)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Student> getAllStudent() {
        return studentService.getStudents();
    }

//    2. Tìm kiếm sinh viên theo tên (GET)
//    3. Lấy chi tiết sinh viên (GET)
//    4. Tạo sinh viên mới (POST)
//    5. Cập nhật thông tin của sinh viên (PUT)
//    6. Xóa sinh viên (DELETE)
}
