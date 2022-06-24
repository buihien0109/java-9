package vn.techmaster.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.todolist.model.Todo;
import vn.techmaster.todolist.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TodoController {

    @Autowired
    private TodoService todoService;

//    Lấy danh sách tất cả công việc
//    http://localhost:8080/api/v1/todos (Lấy tất cả cv)
//    http://localhost:8080/api/v1/todos?status=true (Lấy cv đã hoàn thành)
//    http://localhost:8080/api/v1/todos?status=false (Lấy cv chưa hoàn thành)
    @GetMapping("/todos")
    public List<Todo> getTodos(@RequestParam(required = false, defaultValue = "") String status) {
        return todoService.getTodos(status);
    }
}
