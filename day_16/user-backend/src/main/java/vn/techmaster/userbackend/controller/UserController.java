package vn.techmaster.userbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.userbackend.dto.UserDto;
import vn.techmaster.userbackend.request.UpdatePasswordRequest;
import vn.techmaster.userbackend.request.UpdateUserRequest;
import vn.techmaster.userbackend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    // Lấy danh sách user
    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    // Tìm kiếm user theo tên
    // http://localhost:8080/api/v1/users/search?name=hien
    // http://localhost:8080/api/v1/users/search?name=an
    @GetMapping("/users/search")
    public List<UserDto> searchUser(@RequestParam String name) {
        return userService.searchUser(name);
    }

    // Xóa user theo id
    // DELETE http://localhost:8080/api/v1/users/1
    // DELETE http://localhost:8080/api/v1/users/2
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    // Lấy thông tin của user
    // GET http://localhost:8080/api/v1/users/1
    // GET http://localhost:8080/api/v1/users/2
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // Cập nhật thông tin của user
    // PUT http://localhost:8080/api/v1/users/1
    // PUT http://localhost:8080/api/v1/users/2
    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    // Đổi mật khẩu
    @PutMapping("/users/{id}/update-password")
    public void updatePassword(@PathVariable int id, @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
    }

    // Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public String forgotPassword(@PathVariable int id) {
        return userService.forgotPassword(id);
    }
}
