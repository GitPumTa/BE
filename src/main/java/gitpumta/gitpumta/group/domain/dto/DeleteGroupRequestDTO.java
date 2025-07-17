package gitpumta.gitpumta.group.domain.dto;

import lombok.Getter;
import java.util.UUID;

@Getter
public class DeleteGroupRequestDTO {
    private UUID groupId;
    private UUID requesterId;
}
