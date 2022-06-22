package com.example.student.service;

import com.example.student.exception.NotFoundException;
import com.example.student.model.Student;
import com.example.student.request.CreateStudentRequest;
import com.example.student.request.UpdateStudentRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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

    // Tìm kiếm sinh viên theo tên
    public List<Student> searchStudent(String name) {
        return students.stream()
                .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Lấy thông tin của học viên
    public Student getStudentById(int id) {
        // Cách 1:
//        Optional<Student> studentOptional = findById(id);
//
//        if(studentOptional.isPresent()) {
//            return studentOptional.get();
//        }
//
//        throw new NotFoundException("Không tìm thấy sinh viên có id = " + id);

        // Cách 2:
        return findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy sinh viên có id = " + id);
        });
    }

    public void deleteStudent(int id) {
        // B1 : Kiểm tra xem student có tồn tại hay không
        if(findById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy sinh viên có id = " + id);
        }

        // B2 : Xóa user
        students.removeIf(student -> student.getId() == id);
    }

    public Student createStudent(CreateStudentRequest request) {
        // B1 : Kiểm tra xem email đã tồn tại hay chưa

        // B2 : Tạo mới
        Random rd = new Random();
        Student student = new Student(rd.nextInt(100), request.getName(), request.getEmail());

        students.add(student);
        return student;
    }

    public Student updateStudent(int id, UpdateStudentRequest request) {
        // B1 : Kiểm tra xem student có tồn tại hay không

        for (Student student : students) {
            if(student.getId() == id) {
                student.setName(request.getName());
                return student;
            }
        }
        return null;
    }

    // HELPER METHOD : Tìm kiếm sinh viên theo id -> Optional
    public Optional<Student> findById(int id) {
        return students
                .stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }
}
