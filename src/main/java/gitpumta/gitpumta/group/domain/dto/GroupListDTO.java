package gitpumta.gitpumta.group.domain.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class GroupListDTO {
    private UUID id;
    private String name;
    private String description;
    private Integer capacity;
    private Integer memberCnt; // 현재 가입된 인원 수
}
