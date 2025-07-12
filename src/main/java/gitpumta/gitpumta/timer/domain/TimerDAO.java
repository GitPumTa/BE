package gitpumta.gitpumta.timer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimerDAO {
    @Id
    private UUID id;

    @Column(name = "user_id",nullable = false)
    private UUID userId;

    @Column(name = "planner_id", nullable = false)
    private UUID plannerId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column
    private int time;

    @Column(name = "session_type")
    private String sessionType;

    @Column
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
