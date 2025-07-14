package gitpumta.gitpumta.timer.repository;

import gitpumta.gitpumta.timer.domain.TimerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TimerDAORepository extends JpaRepository<TimerDAO, UUID> {
    //List<TimerDAO> findAllByUserId(UUID userId);

    Optional<TimerDAO> findByUserIdAndPlannerIdAndDeletedAtIsNullAndCreatedAtBetween(
            UUID userId, UUID plannerId, LocalDateTime start, LocalDateTime end);

    //List<TimerDAO> find
}
