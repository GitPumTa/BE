package gitpumta.gitpumta.timer.service;

import gitpumta.gitpumta.timer.bean.StartTimerBean;
import gitpumta.gitpumta.timer.bean.StopTimerBean;
import gitpumta.gitpumta.timer.domain.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TimerService {
    private final StartTimerBean startTimerBean;
    private final StopTimerBean stopTimerBean;

    public TimerService(StartTimerBean startTimerBean, StopTimerBean stopTimerBean) {
        this.startTimerBean = startTimerBean;
        this.stopTimerBean = stopTimerBean;
    }

    public LocalDateTime startTimer(UUID accountId, TimerRequestDTO timerRequestDTO) {
        startTimerBean.exec(accountId, timerRequestDTO);
        return timerRequestDTO.getSend_at();
    }

    public LocalDateTime stopTimer(UUID accountId, TimerRequestDTO timerRequestDTO) {
        stopTimerBean.exec(accountId, timerRequestDTO);
        return timerRequestDTO.getSend_at();
    }
/*
    public GetMemberTimersResponseDTO GetMemberTimers(GetMemberTimersRequestDTO getMemberTimersRequestDTO) {
        // 빈 받아와서 서비스 구현
    }
 */
}
