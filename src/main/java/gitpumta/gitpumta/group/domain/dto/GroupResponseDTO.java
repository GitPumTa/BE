package gitpumta.gitpumta.group.domain.dto;

import lombok.Data;
import lombok.Builder;

import java.util.UUID;
import java.util.List;

@Data
@Builder
public class GroupResponseDTO {
    private UUID id;
    private String name;
    private List<String> rule;
    private String description;
    private Integer capacity;
}
