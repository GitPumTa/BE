package gitpumta.gitpumta.group.repository;

import gitpumta.gitpumta.group.domain.GroupMemberDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupMemberDAORepository extends JpaRepository<GroupMemberDAO, UUID> {

    List<GroupMemberDAO> findByGroupIdAndDeletedAtIsNull(UUID groupId);
    List<GroupMemberDAO> findByUserIdAndDeletedAtIsNull(UUID userId);
    boolean existsByGroupIdAndUserIdAndDeletedAtIsNull(UUID groupId, UUID userId);
    Optional<GroupMemberDAO> findByGroupIdAndUserIdAndDeletedAtIsNull(UUID groupId, UUID userId);
    int countByGroupId_IdAndDeletedAtIsNull(UUID groupId);
}
