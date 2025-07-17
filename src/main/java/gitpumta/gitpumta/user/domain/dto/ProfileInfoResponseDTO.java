package gitpumta.gitpumta.user.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Data
public class ProfileInfoResponseDTO {

    // private UUID id;

    private String accountId;

    private String nickname;

    private Integer totalCommit;

    private Integer totalDuration;
}
