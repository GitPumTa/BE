package gitpumta.gitpumta.user.repository;

import gitpumta.gitpumta.user.domain.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface UserDAORepository extends JpaRepository<UserDAO, UUID> {
    Optional<UserDAO> findByNickname(String nickname);
    List<UserDAO> findAllByOrderByTotalDurationDesc();
    List<UserDAO> findAllByOrderByTotalCommitDesc();
    Optional<UserDAO> findAllById(UUID id);
    Optional<UserDAO> findById(UUID userId);
    Optional<UserDAO> findByAccountId(String accountId);
}
