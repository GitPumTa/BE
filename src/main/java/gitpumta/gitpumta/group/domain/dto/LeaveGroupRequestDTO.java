package gitpumta.gitpumta.group.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LeaveGroupRequestDTO {
    private UUID groupId;
    private UUID userId;
}
