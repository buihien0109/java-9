package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.request.CreateStudentRequest;
import com.example.student.request.UpdateStudentRequest;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //    1. Lấy danh sách sinh viên (GET)
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getAllStudent() {
        return studentService.getStudents();
    }

    //    2. Tìm kiếm sinh viên theo tên (GET)
    // http://localhost:8080/students/search?name=An&gender=male (query string) : Lọc dữ liệu
    // http://localhost:8080/students/search?name=Hien&email=a@gmail.com
    @RequestMapping(value = "/students/search", method = RequestMethod.GET)
    public List<Student> searchUser(@RequestParam String name) {
        return studentService.searchStudent(name);
    }

    //    3. Lấy chi tiết sinh viên (GET)
    // http://localhost:8080/students/1
    // http://localhost:8080/students/10
    // http://localhost:8080/students/5
    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    //    4. Tạo sinh viên mới (POST)
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody CreateStudentRequest request) {
        return studentService.createStudent(request);
    }

    //    5. Cập nhật thông tin của sinh viên (PUT)
    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public Student updateStudent(@PathVariable int id, @RequestBody UpdateStudentRequest request) {
        return studentService.updateStudent(id, request);
    }

    //    6. Xóa sinh viên (DELETE)
    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}
