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

    Optional<TimerDAO> findByUserIdAndDeletedAtIsNullAndCreatedAtBetween(
            UUID userId, LocalDateTime start, LocalDateTime end);

    List<TimerDAO> findByUserIdInAndDeletedAtIsNullOrderByTotalDurationDesc(
            List<UUID> userIds);

    List<TimerDAO> findByUserIdInAndDeletedAtIsNull(List<UUID> userIds);

    // 멤버 타이머 검색 로직에 필요한 쿼리 작성
}
