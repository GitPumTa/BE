package gitpumta.gitpumta.group.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateGroupRequestDTO {
    private UUID id;

    private String name;

    private String password;

    private Integer capacity;

    private String description;
}
