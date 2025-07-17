package gitpumta.gitpumta.todo.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateTodoRequestDTO {
    private UUID id;
    private UUID user_id;
    private String description;
    private Integer capacity;
    private String status;
    private String title;
}
