package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class MainTimerResponseDTO {
    private String message;

    private List<RepoDTO> repos;
}
