package gitpumta.gitpumta.group.domain.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class GroupMemberRoleDTO {
    private UUID userId;
    private UUID groupId;
    private String role; // "LEADER" or "MEMBER"
    private LocalDateTime joinedAt;
}