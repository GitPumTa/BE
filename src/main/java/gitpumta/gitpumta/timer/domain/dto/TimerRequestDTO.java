package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TimerRequestDTO {
    private UUID account_id;
    private LocalDateTime send_at;

    private int status;
    private int total_duration;

    private List<RepoDTO> repos;
}
