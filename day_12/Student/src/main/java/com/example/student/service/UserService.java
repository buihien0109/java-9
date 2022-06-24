package com.example.student.service;

import com.example.student.dto.UserDto;
import com.example.student.exception.BadRequestException;
import com.example.student.exception.NotFoundException;
import com.example.student.mapper.UserMapper;
import com.example.student.model.User;
import com.example.student.request.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User(1, "bob", "bob@gmail.com", "111", "https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cGVyc29ufGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60"));
        users.add(new User(2, "anna", "anna@gmail.com", "222", "https://images.unsplash.com/photo-1499952127939-9bbf5af6c51c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fHBlcnNvbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60"));
        users.add(new User(3, "john", "john@gmail.com", "333", "https://images.unsplash.com/photo-1500048993953-d23a436266cf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fHBlcnNvbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60"));
    }

    // Dạng DTO : Data transfer object
    public UserDto login(LoginRequest request) {
        // Kiểm tra username hoặc password có để trống hay không
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new BadRequestException("username hoặc password không được để trống");
        }

        // Tìm kiếm user
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getUsername().equals(request.getUsername()) && user.getPassword().equals(request.getPassword()))
                .findFirst();

        // Nếu không tìm thấy thì báo lỗi
        if (userOptional.isEmpty()) {
            throw new NotFoundException("username hoặc password không chính xác");
        }

        // Nếu tìm thấy thì trả về thông tin của user ở dạng DTO
        return UserMapper.toUserDto(userOptional.get());
    }
}
