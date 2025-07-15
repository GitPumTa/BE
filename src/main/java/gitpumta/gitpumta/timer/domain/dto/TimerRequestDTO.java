package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TimerRequestDTO {
    private LocalDateTime send_at;

    private int status;

    private int total_duration;

    private List<RepoDTO> repos;
}
