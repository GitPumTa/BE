package gitpumta.gitpumta.todo.controller;

import gitpumta.gitpumta.group.service.GroupService;
import gitpumta.gitpumta.todo.domain.dto.CreateTodoRequestDTO;
import gitpumta.gitpumta.todo.domain.dto.TodoDetailResponseDTO;
import gitpumta.gitpumta.todo.domain.dto.UpdateTodoRequestDTO;
import gitpumta.gitpumta.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/todo")
@CrossOrigin
public class TodoController {
    private final TodoService todoService;
    private final RestClient.Builder builder;

    public TodoController(TodoService todoService, RestClient.Builder builder, GroupService groupService) {
        this.todoService = todoService;
        this.builder = builder;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Map<String, Object>> createTodo(@RequestBody CreateTodoRequestDTO createTodoRequestDTO) {
        UUID id = todoService.createTodo(createTodoRequestDTO);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("id", id);
        requestMap.put("message", id != null? "투두 생성" : "투두 생성 실패");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    @GetMapping(value = "/list/{user_id}")
    public ResponseEntity<?> getTodoListByUserid(@PathVariable UUID user_id) {
        return ResponseEntity.ok(todoService.getAllTodosByUserId(user_id));
    }

    @GetMapping(value = "/list/{user_id}/{title}")
    public ResponseEntity<?> getTodoByUserIdAndTitle(@PathVariable UUID user_id, @PathVariable String title) {
        return ResponseEntity.ok(todoService.getTodoByUserIdAndTitle(user_id, title));
    }
}