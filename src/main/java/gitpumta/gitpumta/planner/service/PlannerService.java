package gitpumta.gitpumta.planner.service;

import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.planner.bean.CreatePlannerBean;
import gitpumta.gitpumta.planner.domain.PlannerDAO;
import gitpumta.gitpumta.planner.domain.dto.CreatePlannerRequestDTO;
import gitpumta.gitpumta.planner.domain.dto.PlannerResponseDTO;
import gitpumta.gitpumta.planner.repository.PlannerDAORepository;
import gitpumta.gitpumta.todo.domain.dto.TodoResponseDTO;
import gitpumta.gitpumta.todo.repository.TodoDAOReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlannerService {
    private final CreatePlannerBean createPlannerBean;
    private final PlannerDAORepository plannerRepository;

    public PlannerService(CreatePlannerBean createPlannerBean, PlannerDAORepository plannerRepository) {
        this.createPlannerBean = createPlannerBean;
        this.plannerRepository = plannerRepository;
    }

    //플래너 생성
    public UUID createPlanner(CreatePlannerRequestDTO createPlannerRequestDTO) {
        PlannerDAO plannerDAO = createPlannerBean.exec(createPlannerRequestDTO);
        plannerRepository.save(plannerDAO);
        return plannerDAO.getId();
    }

    // 플래너 유저기반 조회
    public List<PlannerResponseDTO> getAllPlannersByUserId(UUID userId) {
        return plannerRepository.findByUserId(userId)
                .stream()
                .map(planner -> PlannerResponseDTO.builder()
                        .name(planner.getName())
                        .description(planner.getDescription())
                        .repository_link(planner.getRepository_link())
                        .duration(planner.getDuration())
                        .build())
                .collect(Collectors.toList());
    }

    // 플래너 유저기반 상세조회
    public PlannerResponseDTO getPlannerByUserIdAndName(UUID userId, String name) {
        return plannerRepository.findByUserIdAndName(userId, name)
                .stream()
                .findFirst() // 첫 번째 결과만 꺼냄
                .map(planner -> PlannerResponseDTO.builder()
                        .name(planner.getName())
                        .description(planner.getDescription())
                        .repository_link(planner.getRepository_link())
                        .duration(planner.getDuration())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("해당 Todo 없음"));
    }
}