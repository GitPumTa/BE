package gitpumta.gitpumta.user.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetUserResponseDTO {

    private UUID id;

    private String accountId;

    private String password;
    private String nickname;

    private String gitId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
