package gitpumta.gitpumta.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingResponseDTO {
    private List<UserRatingInfoDTO> timeRanking;
    private List<UserRatingInfoDTO> commitRanking;
}
