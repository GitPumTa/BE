package gitpumta.gitpumta.group.repository;

import gitpumta.gitpumta.group.domain.GroupDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface GroupDAORepository extends JpaRepository<GroupDAO, UUID> {
    List<GroupDAO> findByDeletedAtIsNull();
    List<GroupDAO> findByIdInAndDeletedAtIsNull(List<UUID> groupIds);
    List<GroupDAO> findByNameContainingAndDeletedAtIsNull(String keyword);
    List<GroupDAO> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeletedAtIsNull(String name, String description);
    Optional<GroupDAO> findByIdAndDeletedAtIsNull(UUID id);
}