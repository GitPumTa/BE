package gitpumta.gitpumta.planner.bean;

import gitpumta.gitpumta.planner.domain.PlannerDAO;
import gitpumta.gitpumta.planner.domain.dto.CreatePlannerRequestDTO;
import gitpumta.gitpumta.planner.repository.PlannerDAORepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePlannerBean {
    private PlannerDAORepository repository;
    public CreatePlannerBean(PlannerDAORepository repository) {
        this.repository = repository;
    }
    public PlannerDAO exec(CreatePlannerRequestDTO createPlannerRequestDTO) {
        PlannerDAO planner = PlannerDAO.builder()
                .id(UUID.randomUUID())
                //.userId(createPlannerRequestDTO.getUser_id())
                .name(createPlannerRequestDTO.getName())
                .description(createPlannerRequestDTO.getDescription())
                .repository_link(createPlannerRequestDTO.getRepository_link())
                .build();
        return repository.save(planner);
    }
}
