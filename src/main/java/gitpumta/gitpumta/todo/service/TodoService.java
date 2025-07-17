package gitpumta.gitpumta.todo.service;

import gitpumta.gitpumta.todo.bean.CreateTodoBean;
import gitpumta.gitpumta.todo.domain.TodoDAO;
import gitpumta.gitpumta.todo.domain.dto.CreateTodoRequestDTO;
import gitpumta.gitpumta.todo.domain.dto.TodoResponseDTO;
import gitpumta.gitpumta.todo.domain.dto.UpdateTodoRequestDTO;
import gitpumta.gitpumta.todo.repository.TodoDAOReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gitpumta.gitpumta.todo.domain.dto.TodoResponseDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final CreateTodoBean createTodoBean;
    private TodoDAOReopsitory todoRepository;

    public TodoService(CreateTodoBean createTodoBean, TodoDAOReopsitory todoRepository) {
        this.createTodoBean = createTodoBean;
        this.todoRepository = todoRepository;
    }

    public UUID createTodo(CreateTodoRequestDTO createTodoRequestDTO){
        TodoDAO todoDAO = createTodoBean.exec(createTodoRequestDTO);
        todoRepository.save(todoDAO);
        return todoDAO.getId();
    }

    // todo 유저기반 조회
    public List<TodoResponseDTO> getAllTodosByUserId(UUID userId) {
        return todoRepository.findByUserId(userId)
                .stream()
                .map(todo -> TodoResponseDTO.builder()
                        .title(todo.getTitle())
                        .status(todo.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    // todo 유저기반 상세조회
    public TodoResponseDTO getTodoByUserIdAndTitle(UUID userId, String title) {
        return todoRepository.findByUserIdAndTitle(userId, title)
                .stream()
                .findFirst() // 첫 번째 결과만 꺼냄
                .map(todo -> TodoResponseDTO.builder()
                        .title(todo.getTitle())
                        .description(todo.getDescription())
                        .status(todo.getStatus())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("해당 Todo 없음"));
    }
}
