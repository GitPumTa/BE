package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GetMemberTimersRequestDTO {
    private UUID accountId;
}
