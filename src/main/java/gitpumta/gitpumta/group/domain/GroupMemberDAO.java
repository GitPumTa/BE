package gitpumta.gitpumta.group.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gitpumta.gitpumta.user.domain.UserDAO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "group_member")
public class GroupMemberDAO {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "group_id", columnDefinition = "BINARY(16)")
    private UUID groupId;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
