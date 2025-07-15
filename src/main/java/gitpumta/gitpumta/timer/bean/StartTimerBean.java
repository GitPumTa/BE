package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.timer.domain.TimerDAO;
import gitpumta.gitpumta.timer.domain.dto.TimerRequestDTO;
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
/*
    public void exec(StartTimerRequestDTO startTimerRequestDTO) {
        UUID userId = startTimerRequestDTO.getAccountId();
        UUID plannerId = startTimerRequestDTO.getPlannerId();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay(); // 해당 날짜 00시 00분
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1); // 해당 날짜 23시 59분

        // 동작중인 타이머 하나로만, 들어온 요청 타이머 제외 타이머 정지?
        TimerDAO timer = timerDAORepository.findByUserIdAndPlannerIdAndDeletedAtIsNullAndCreatedAtBetween(
                        userId, plannerId, startOfDay, endOfDay)
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
    }*/
    public void exec(UUID accountId, TimerRequestDTO timerRequestDTO) {
        int totalDuration = timerRequestDTO.getTotal_duration();
        LocalDateTime now = timerRequestDTO.getSend_at();

        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay(); // 해당 날짜 00시 00분
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1); // 해당 날짜 23시 59분

        TimerDAO timer = timerDAORepository.findByUserIdAndDeletedAtIsNullAndCreatedAtBetween(
                accountId, startOfDay, endOfDay)
                .map(existing -> {
                    existing.setStatus("0");
                    existing.setUpdatedAt(now);
                    existing.setTotalDuration(totalDuration);

                    return existing;
                })
                .orElseGet(() -> TimerDAO.builder()
                        .id(UUID.randomUUID())
                        .userId(accountId)
                        .updatedAt(now)
                        .totalDuration(totalDuration)
                        .status("0")
                        .createdAt(now)
                        .build());

        timerDAORepository.save(timer);

        // repo 접근 여기서 해야할듯 (각 repo당 duration 저장 등)
    }
}
