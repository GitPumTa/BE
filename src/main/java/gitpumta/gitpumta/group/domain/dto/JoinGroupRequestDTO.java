package gitpumta.gitpumta.group.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JoinGroupRequestDTO {
    private UUID groupId;
    private String password;
}
