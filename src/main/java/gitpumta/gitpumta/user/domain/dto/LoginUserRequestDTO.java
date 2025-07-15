package gitpumta.gitpumta.user.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginUserRequestDTO {
    private String accountId;
    private String password;
}
