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

    public void exec(String accountId, TimerRequestDTO timerRequestDTO) {
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
