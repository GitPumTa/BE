package gitpumta.gitpumta.commit.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateCommitRequestDTO {
    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("planner_id")
    private UUID plannerId;

    private List<CommitListDTO> commits;

    // getter, setter, (생성자 필요시 추가)
}