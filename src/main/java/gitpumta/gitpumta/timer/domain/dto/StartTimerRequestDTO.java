package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StartTimerRequestDTO {
    private UUID accountId;

    private UUID plannerId;

    private String sessionType;
}
