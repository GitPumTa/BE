package gitpumta.gitpumta.group.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroupDAO {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String password;

    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> rule;

    @Column(name = "member_count", nullable = false)
    private Integer memberCnt;

    // 기본 생성자, getter, setter 생략 (IDE로 자동 생성 권장)
}
