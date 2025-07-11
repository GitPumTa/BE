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
public class UserDAO {
    @Id
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

    // Getters, Setters, Constructors
}
