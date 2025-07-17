package gitpumta.gitpumta.timer.bean.samll;

import gitpumta.gitpumta.planner.repository.PlannerDAORepository;
import gitpumta.gitpumta.timer.domain.dto.TimerRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UpdatePlannerBean {
    private final PlannerDAORepository plannerDAORepository;

    public UpdatePlannerBean(PlannerDAORepository plannerDAORepository) {
        this.plannerDAORepository = plannerDAORepository;
    }

    // 각 Repo id로 Planner 찾아서 duration 수정
    public void updateDurations(List<TimerRequestDTO.RepoDTO> repos, LocalDateTime now) {
        if (repos == null) return;

        for (TimerRequestDTO.RepoDTO repo : repos) {
            plannerDAORepository.findById(repo.getId()).ifPresent(planner -> {
                //planner.setDuration(repo.getDuration());
                planner.setUpdatedAt(now);
                plannerDAORepository.save(planner);
            });
        }
    }
}
