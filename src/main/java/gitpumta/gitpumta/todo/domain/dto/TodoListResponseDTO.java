package gitpumta.gitpumta.todo.domain.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TodoListResponseDTO {
    private UUID id;

    private List<TodoResponseDTO> todos;
}