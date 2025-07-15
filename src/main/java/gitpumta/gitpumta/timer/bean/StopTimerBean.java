package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.timer.domain.TimerDAO;
import gitpumta.gitpumta.timer.domain.dto.TimerRequestDTO;
import gitpumta.gitpumta.timer.repository.TimerDAORepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class StopTimerBean {
    private final TimerDAORepository timerDAORepository;

    public StopTimerBean(TimerDAORepository timerDAORepository) {
        this.timerDAORepository = timerDAORepository;
    }
/*
    public void exec(StopTimerRequestDTO stopTimerRequestDTO) {
        UUID userId = stopTimerRequestDTO.getAccountId();
        UUID plannerId = stopTimerRequestDTO.getPlannerId();
        String status = stopTimerRequestDTO.getStatus();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay(); // 해당 날짜 00시 00분
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1); // 해당 날짜 23시 59분

        TimerDAO timer = timerDAORepository.findByUserIdAndPlannerIdAndDeletedAtIsNullAndCreatedAtBetween(
                        userId, plannerId, startOfDay, endOfDay)
                .orElseThrow(() -> new IllegalStateException("정지할 타이머가 없습니다."));

        long duration = ChronoUnit.SECONDS.between(timer.getUpdatedAt(), now);

        timer.setTime(timer.getTime() + (int)duration);
        timer.setUpdatedAt(now);
        timer.setStatus(status);

        timerDAORepository.save(timer);
    }
 */

    public void exec(UUID accountId, TimerRequestDTO timerRequestDTO) {
        int totalDuration = timerRequestDTO.getTotal_duration();
        LocalDateTime now = timerRequestDTO.getSend_at();

        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay(); // 해당 날짜 00시 00분
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1); // 해당 날짜 23시 59분

        TimerDAO timer = timerDAORepository.findByUserIdAndDeletedAtIsNullAndCreatedAtBetween(
                accountId, startOfDay, endOfDay)
                .orElseThrow(() -> new IllegalStateException("정지할 타이머가 없습니다."));

        timer.setStatus("1");
        timer.setUpdatedAt(now);
        timer.setTotalDuration(totalDuration);

        timerDAORepository.save(timer);
    }
}
