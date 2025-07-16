package gitpumta.gitpumta.todo.bean;

import gitpumta.gitpumta.todo.domain.TodoDAO;
import gitpumta.gitpumta.todo.domain.dto.CreateTodoRequestDTO;
import gitpumta.gitpumta.todo.repository.TodoDAOReopsitory;
import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
public class CreateTodoBean {
    private TodoDAOReopsitory reopsitory;

    public CreateTodoBean(TodoDAOReopsitory reopsitory) { this.reopsitory = reopsitory; }

    public TodoDAO exec(CreateTodoRequestDTO createTodoRequestDTO) {
        TodoDAO todo = TodoDAO.builder()
                .id(UUID.randomUUID())
                .plannerId(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .title(createTodoRequestDTO.getTitle())
                .description(createTodoRequestDTO.getDescription())
                .status(createTodoRequestDTO.getStatus())
                .dueDate(createTodoRequestDTO.getDueDate())
                .build();
        return reopsitory.save(todo);
    }
}
