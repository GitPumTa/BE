package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimerResponseDTO {
    private String message;
    private LocalDateTime initial_start;

    public TimerResponseDTO() {}

    public TimerResponseDTO(String message, LocalDateTime initial_start) {
        this.message = message;
        this.initial_start = initial_start;
    }
}
