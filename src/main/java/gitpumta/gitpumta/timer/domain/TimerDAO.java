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
    private String userId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "total_duration")
    private int totalDuration;

    @Column
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
