package gitpumta.gitpumta.timer.scheduler;

import gitpumta.gitpumta.timer.domain.TimerDAO;
import gitpumta.gitpumta.timer.repository.TimerDAORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// 00시 기준 타이머 초기화 로직
@Component
@RequiredArgsConstructor
public class TimerScheduler {/*
    private final TimerDAORepository timerDAORepository;

    // 00시
    @Scheduled(cron = "0 0 12 * * *")
    public void resetAndCreateTimers() {
        LocalDateTime now = LocalDateTime.now();

        List<TimerDAO> activeTimers = timerDAORepository.findAllByStatus("1");

        for (TimerDAO timer : activeTimers) {
            timer.setStatus("-1"); // 완전 정지
            timer.setUpdatedAt(now);
            timerDAORepository.save(timer);
        }

        // 새로 타이머 생성
        for (TimerDAO oldTimer : activeTimers) {
            TimerDAO newTimer = TimerDAO.builder()
                    .id(UUID.randomUUID())
                    .userId(oldTimer.getUserId())
                    .plannerId(oldTimer.getPlannerId())
                    .sessionType(oldTimer.getSessionType())
                    .status("1")
                    .createdAt(now)
                    .updatedAt(now)
                    .time(0)
                    .build();

            timerDAORepository.save(newTimer);
        }
    }*/
}
