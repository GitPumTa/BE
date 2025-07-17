package gitpumta.gitpumta.group.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KickOutRequestDTO {
    private UUID groupId;
    private UUID targetUserId;
    private UUID requesterId;
}