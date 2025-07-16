package gitpumta.gitpumta.commit.repository;

import gitpumta.gitpumta.user.domain.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommitDAORepository extends JpaRepository<UserDAO, UUID> {

}
