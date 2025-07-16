package gitpumta.gitpumta.planner.repository;

import gitpumta.gitpumta.planner.domain.PlannerDAO;
import gitpumta.gitpumta.todo.domain.TodoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlannerDAORepository extends JpaRepository<PlannerDAO, UUID> {
    List<PlannerDAO> findByUserId(UUID userId);

    Optional<PlannerDAO> findByUserIdAndName(UUID userId, String name);
}
