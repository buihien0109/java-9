package com.example.student.controller;

import com.example.student.dto.UserDto;
import com.example.student.request.LoginRequest;
import com.example.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody // Thêm vào nếu như sử dụng @Controller. Còn nếu sử dụng @RestController thì bỏ qua
    public UserDto login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    /*
     Khi sử dụng ResponseEntity thì chúng ta không cần quan tâm @Controller hay @RestController
     Mặc định dữ liệu trả về sẽ nằm trong body của response
     Ngoài ra khi sử dụng ResponseEntity có thể bổ sung thêm nhiều thông tin cho response. Ví dụ : Content-type, key-value, ...

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        UserDto userDto = userService.login(request);
        
        return ResponseEntity.ok(userDto); (Cách 1)
        return ResponseEntity.ok().body(userDto); (Cách 2)
        return new ResponseEntity<>(userDto, HttpStatus.OK); (Cách 3)
    }
    */
}
