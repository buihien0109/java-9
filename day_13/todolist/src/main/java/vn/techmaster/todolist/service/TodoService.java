package vn.techmaster.todolist.service;

import org.springframework.stereotype.Service;
import vn.techmaster.todolist.exception.BadRequestException;
import vn.techmaster.todolist.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private List<Todo> todos;

    public TodoService() {
        Random rd = new Random();
        todos = new ArrayList<>();
        todos.add(new Todo(rd.nextInt(100), "Đi chơi", true));
        todos.add(new Todo(rd.nextInt(100), "Làm bài tập", false));
        todos.add(new Todo(rd.nextInt(100), "Đi nhật", true));
    }

    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "" -> todos;
            case "true" -> todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
            default -> throw new BadRequestException("Trạng thái không hợp lệ");
        };
    }
}
