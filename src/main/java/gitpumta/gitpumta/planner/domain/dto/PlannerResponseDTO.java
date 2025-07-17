package gitpumta.gitpumta.planner.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlannerResponseDTO {
    private UUID id;
    private UUID userId;
    private String description;
    private String name;
    private String repository_link;
    private int duration;
}