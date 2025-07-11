package gitpumta.gitpumta.group.repository;

import gitpumta.gitpumta.group.domain.GroupDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupDAORepository extends JpaRepository<GroupDAO, UUID> {
}
