package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.planner.domain.PlannerDAO;
import gitpumta.gitpumta.planner.repository.PlannerDAORepository;
import gitpumta.gitpumta.timer.domain.dto.RepoDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MainTimerRepoBean {
    private final PlannerDAORepository plannerDAORepository;

    public MainTimerRepoBean(PlannerDAORepository plannerDAORepository) {
        this.plannerDAORepository = plannerDAORepository;
    }

    public List<RepoDTO> exec(UUID accountId) {
        List<PlannerDAO> planners = plannerDAORepository.findByUserId(accountId.toString());

        return planners.stream().map(planner -> {
            RepoDTO repoDTO = new RepoDTO();
            repoDTO.setId(planner.getId());
            repoDTO.setTitle(planner.getName());
            repoDTO.setSubtitle(planner.getDescription());
            repoDTO.setRepo_address(planner.getRepository_link());
            //repoDTO.setDuration(0);
            return repoDTO;
        }).collect(Collectors.toList());
    }
}
