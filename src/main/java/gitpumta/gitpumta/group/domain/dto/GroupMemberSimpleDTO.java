package gitpumta.gitpumta.group.domain.dto;

import gitpumta.gitpumta.group.domain.GroupMemberDAO;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class GroupMemberSimpleDTO {

    private final UUID userId;
    private final LocalDateTime joinedAt;

    public GroupMemberSimpleDTO(GroupMemberDAO dao) {
        this.userId = dao.getUser().getId();
        this.joinedAt = dao.getJoinedAt();
    }
}
