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
public interface TodoDAOReopsitory extends JpaRepository<TodoDAO, String> {
    List<TodoDAO> findByUserId(String userId);

    Optional<TodoDAO> findByUserIdAndTitle(String userId, String title);

}
