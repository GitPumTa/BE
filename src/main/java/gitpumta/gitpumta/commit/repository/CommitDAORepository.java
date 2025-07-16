package gitpumta.gitpumta.commit.repository;

import gitpumta.gitpumta.commit.domain.CommitDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface CommitDAORepository extends JpaRepository<CommitDAO, UUID> {
    int countByPlannerIdAndCreatedAtBetweenAndDeletedAtIsNull(
            UUID plannerId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
