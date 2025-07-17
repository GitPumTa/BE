package gitpumta.gitpumta.todo.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateTodoRequestDTO {
    private UUID id;

    private UUID plannerId;

    private UUID userId;

    private String title;

    private String status;

    private String description;

    private LocalDateTime dueDate;
}
