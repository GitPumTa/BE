package gitpumta.gitpumta.planner.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlannerResponseDTO {
    private UUID id;
    private String description;
    private String name;
    private String repository_link;
}