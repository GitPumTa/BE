package gitpumta.gitpumta.user.domain;


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
@Table(name="user")
public class UserDAO {

    @Id
    @Column(length = 36)
    private UUID id;

    @Column(name = "account_id", nullable = false, unique = true)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "git_id")
    private String gitId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "total_duration")
    private Integer totalDuration;

    @Column(name = "total_commit")
    private Integer totalCommit;

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public Integer getTotalCommit() {
        return totalCommit;
    }
    // Getters, Setters, Constructors
}
