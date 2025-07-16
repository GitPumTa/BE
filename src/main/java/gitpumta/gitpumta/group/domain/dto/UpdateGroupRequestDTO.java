package gitpumta.gitpumta.group.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateGroupRequestDTO {
    private UUID groupId;
    private String name;
    private String description;
    private String password;
    private Integer capacity;
    private List<String> rule;
}
