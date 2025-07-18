package gitpumta.gitpumta.group.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;
import java.util.List;

@Data
public class CreateGroupRequestDTO {

    private UUID userId;

    private String name;

    private List<String> rule;

    private String password;

    private Integer capacity;

    private String description;
}
