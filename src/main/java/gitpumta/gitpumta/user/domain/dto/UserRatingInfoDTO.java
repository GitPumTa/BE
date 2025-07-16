package gitpumta.gitpumta.user.domain.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRatingInfoDTO {
    private int rank;

    private String nickname;

    private Integer timeDuration;

    private Integer commitDuration;
}
