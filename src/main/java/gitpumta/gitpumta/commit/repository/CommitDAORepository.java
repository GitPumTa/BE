package gitpumta.gitpumta.commit.repository;

import gitpumta.gitpumta.commit.domain.CommitDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface CommitDAORepository extends JpaRepository<CommitDAO, UUID> {
    CommitDAO findTop1ByPlannerIdOrderByTimeDesc(UUID plannerId);
    CommitDAO findByPlannerId(UUID plannerId);

    int countByPlannerIdAndTimeBetween(UUID plannerId, LocalDateTime timeAfter, LocalDateTime timeBefore);

    int countByPlannerIdAndCreatedAtBetweenAndDeletedAtIsNull(UUID plannerId, LocalDateTime createdAtAfter, LocalDateTime createdAtBefore);
    int countByUserIdAndTimeBetweenAndDeletedAtIsNull(
            UUID userId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
