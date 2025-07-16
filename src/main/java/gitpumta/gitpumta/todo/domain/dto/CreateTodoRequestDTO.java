package gitpumta.gitpumta.todo.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateTodoRequestDTO {
    private UUID id;

    private String plannerId;

    private String userId;

    private String title;

    private String status;

    private LocalDateTime dueDate;
}
