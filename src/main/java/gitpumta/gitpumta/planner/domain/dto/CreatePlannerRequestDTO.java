package gitpumta.gitpumta.planner.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePlannerRequestDTO {
    private UUID id;

    private String user_id;

    private String name;

    private String description;

    private String repository_link;
}