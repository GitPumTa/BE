package gitpumta.gitpumta.user.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GetUserRequestDTO {
    UUID userId;
}
