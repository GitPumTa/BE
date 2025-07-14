package gitpumta.gitpumta.timer.service;

import gitpumta.gitpumta.timer.bean.StartTimerBean;
import gitpumta.gitpumta.timer.bean.StopTimerBean;
import gitpumta.gitpumta.timer.domain.dto.StartTimerRequestDTO;
import gitpumta.gitpumta.timer.domain.dto.StopTimerRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class TimerService {
    private final StartTimerBean startTimerBean;
    private final StopTimerBean stopTimerBean;

    public TimerService(StartTimerBean startTimerBean, StopTimerBean stopTimerBean) {
        this.startTimerBean = startTimerBean;
        this.stopTimerBean = stopTimerBean;
    }

    public void StartTimer(StartTimerRequestDTO startTimerRequestDTO) {
        startTimerBean.exec(startTimerRequestDTO);
    }

    public void StopTimer(StopTimerRequestDTO stopTimerRequestDTO) {
        String status = stopTimerRequestDTO.getStatus();

        if ("0".equals(status) || "-1".equals(status)) {
            stopTimerBean.exec(stopTimerRequestDTO);
        } else {
            throw new IllegalArgumentException("Status값이 잘못되었습니다: " + status);
        }
    }
}
