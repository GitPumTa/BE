package gitpumta.gitpumta.todo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class TodoDetailResponseDTO {
    private UUID id;
    private String description;
    private Integer capacity;
    private String status;
    private String title;
}
