package gitpumta.gitpumta.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {
//            "id": "12345"
//            "nickname": "hoho",
//            "git_id": "user@exampe.com",
//            "created_at": "2024-05-10T00:00:00Z"
    private UUID id;

    private String nickname;

    private String gitId;

    private LocalDateTime createdAt;
}
