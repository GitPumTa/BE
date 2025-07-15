package gitpumta.gitpumta.group.domain.dto;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// 그룹 상세 목록 조회
public class GroupResponseDTO {
    private UUID id;
    private String name;
    private List<String> rule;
    private String description;
    private Integer capacity;
}
