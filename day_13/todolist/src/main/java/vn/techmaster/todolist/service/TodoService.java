package vn.techmaster.todolist.service;

import org.springframework.stereotype.Service;
import vn.techmaster.todolist.exception.BadRequestException;
import vn.techmaster.todolist.exception.NotFoundException;
import vn.techmaster.todolist.model.Todo;
import vn.techmaster.todolist.request.CreateTodoRequest;
import vn.techmaster.todolist.request.UpdateTodoRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private List<Todo> todos;

    public TodoService() {
        todos = new ArrayList<>();
        todos.add(new Todo(1, "Đi chơi", true));
        todos.add(new Todo(2, "Làm bài tập", false));
        todos.add(new Todo(3, "Đi nhật", true));
    }

    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "" -> todos;
            case "true" -> todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
            default -> throw new BadRequestException("Trạng thái không hợp lệ");
        };
    }

    public Todo getTodoById(int id) {
        return findTodoById(id).orElseThrow(() -> {
            throw new NotFoundException("Khong tim thay todo co id = " + id);
        });
    }

    public Todo createTodo(CreateTodoRequest request) {
        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(100), request.getTitle(), false);
        todos.add(todo);
        return todo;
    }

    public Todo updateTodo(int id, UpdateTodoRequest request) {
//        Optional<Todo> todoOptional = findTodoById(id);
//        if (todoOptional.isEmpty()) {
//            throw new NotFoundException("Khong tim thay todo co id =" + id);
//        }

        Todo todo = findTodoById(id).orElseThrow(() -> {
            throw new NotFoundException("Khong tim thay todo co id = " + id);
        });

        todo.setTitle(request.getTitle());
        todo.setStatus(request.isStatus());

        return todo;
    }

    public void deleteTodo(int id) {
        if (findTodoById(id).isEmpty()) {
            throw new NotFoundException("Khong tim thay todo co id =" + id);
        }
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Optional<Todo> findTodoById(int id) {
        return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();
    }
}
