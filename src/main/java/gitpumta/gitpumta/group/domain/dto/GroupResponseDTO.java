package gitpumta.gitpumta.group.domain.dto;

import lombok.Data;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
=======
>>>>>>> dev/group_create
import lombok.Builder;

import java.util.UUID;
import java.util.List;

@Data
@Builder
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDTO {
    private UUID id;
    private String name;
=======
public class GroupResponseDTO {
    private UUID id;
    private String name;
    private List<String> rule;
>>>>>>> dev/group_create
    private String description;
    private Integer capacity;
}
