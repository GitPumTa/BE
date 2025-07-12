package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.timer.domain.TimerDAO;
import gitpumta.gitpumta.timer.domain.dto.StartTimerRequestDTO;
import gitpumta.gitpumta.timer.repository.TimerDAORepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class StartTimerBean {
    private final TimerDAORepository timerDAORepository;
    public StartTimerBean(TimerDAORepository timerDAORepository) {
        this.timerDAORepository = timerDAORepository;
    }

    public void exec(StartTimerRequestDTO startTimerRequestDTO) {
        UUID userId = startTimerRequestDTO.getAccountId();
        UUID plannerId = startTimerRequestDTO.getPlannerId();
        LocalDateTime now = LocalDateTime.now();

        TimerDAO timer = timerDAORepository.findByUserIdAndPlannerIdAndDeletedAtIsNull(userId, plannerId)
                        .map(existing -> {
                            existing.setStatus("1");
                            existing.setUpdatedAt(now);
                            existing.setSessionType(startTimerRequestDTO.getSessionType());
                            return existing;
                        })
                                .orElseGet(() -> TimerDAO.builder()
                                        .id(UUID.randomUUID())
                                        .userId(userId)
                                        .plannerId(plannerId)
                                        .sessionType(startTimerRequestDTO.getSessionType())
                                        .status("1")
                                        .updatedAt(now)
                                        .createdAt(now)
                                        .time(0)
                                        .build());

        timerDAORepository.save(timer);
    }
}
