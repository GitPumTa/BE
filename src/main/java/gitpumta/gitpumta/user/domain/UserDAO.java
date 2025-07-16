package gitpumta.gitpumta.user.domain;


import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.*;

import java.time.*;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDAO {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "account_id", columnDefinition = "BINARY(16)")
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "git_id")
    private String gitId;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
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
