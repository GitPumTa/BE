package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestHeader;

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

    @Data
    public static class RepoDTO {
        private UUID id;

        private String title;

        private String subtitle;

        private String repo_address;

        private int duration;
    }
}
