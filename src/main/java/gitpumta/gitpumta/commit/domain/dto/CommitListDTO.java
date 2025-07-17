package gitpumta.gitpumta.commit.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommitListDTO {
    @JsonProperty("message")
    private String message;

    @JsonProperty("time")
    private LocalDateTime time;
}
