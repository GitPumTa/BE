package gitpumta.gitpumta.user.repository;

import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.GetUserResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserDAORepository extends JpaRepository<UUID, UserDAO> {
    GetUserResponseDTO findById(UUID userId);
}
