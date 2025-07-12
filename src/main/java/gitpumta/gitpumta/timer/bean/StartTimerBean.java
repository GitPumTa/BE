package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.timer.repository.TimerDAORepository;
import org.springframework.stereotype.Component;

@Component
public class GetTimerBean {
    private final TimerDAORepository timerDAORepository;
    public GetTimerBean(TimerDAORepository timerDAORepository) { this.timerDAORepository = timerDAORepository; }


}
