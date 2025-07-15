package gitpumta.gitpumta.user.domain.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignUpUserDTO {
    private String accountId;

    private String password;

    private String nickname;

    private String gitId;
}
