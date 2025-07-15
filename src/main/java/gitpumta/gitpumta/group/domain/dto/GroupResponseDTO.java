package gitpumta.gitpumta.group.domain.dto;

import lombok.*;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

// 그룹 상세 목록 조회
public class GroupResponseDTO {
    private UUID id;
    private String name; // 그룹명
    private List<String> rule; // 그룹 규칙
    private String description; // 그룹 설명
    private Integer capacity; // 그룹 가입 정원
    private Integer memberCnt; // 현재 가입 인원
}
