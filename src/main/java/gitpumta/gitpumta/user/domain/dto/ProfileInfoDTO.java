package gitpumta.gitpumta.user.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileInfoDTO {
    private UUID Id;
}