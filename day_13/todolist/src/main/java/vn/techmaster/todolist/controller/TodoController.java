package vn.techmaster.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.todolist.model.Todo;
import vn.techmaster.todolist.request.CreateTodoRequest;
import vn.techmaster.todolist.request.UpdateTodoRequest;
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

    // Tìm công việc theo id
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id){
        return todoService.getTodoById(id);
    }
    //    3. Tạo mới todos
    @PostMapping("/todos")
    public Todo createTodo(@RequestBody CreateTodoRequest request){
        return todoService.createTodo(request);
    }
    //    4. Cập nhật todos
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request){
        return todoService.updateTodo(id,request);
    }
    //    5. Xóa todos
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable int id){
        todoService.deleteTodo(id);
    }
}
