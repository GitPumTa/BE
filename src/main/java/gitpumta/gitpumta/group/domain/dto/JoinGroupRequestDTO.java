package gitpumta.gitpumta.group.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class JoinGroupRequestDTO {
    private UUID userId; // 테스트용 UUID
    private UUID groupId;
    private String password;
}
