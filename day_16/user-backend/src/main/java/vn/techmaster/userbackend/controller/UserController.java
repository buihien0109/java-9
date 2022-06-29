package vn.techmaster.userbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.userbackend.dto.UserDto;
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
    public void getUserById(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
