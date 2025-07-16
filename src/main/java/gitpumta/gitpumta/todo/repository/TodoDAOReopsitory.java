package gitpumta.gitpumta.todo.repository;

import gitpumta.gitpumta.todo.domain.TodoDAO;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoDAOReopsitory extends JpaRepository<TodoDAO, UUID> {
    List<TodoDAO> findByUserId(UUID userId);

    Optional<TodoDAO> findByUserIdAndTitle(UUID userId, String title);

}
