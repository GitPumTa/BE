package gitpumta.gitpumta.planner.controller;

import gitpumta.gitpumta.planner.domain.dto.CreatePlannerRequestDTO;
import gitpumta.gitpumta.planner.service.PlannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/planner")
@CrossOrigin
public class PlannerController {
    private final PlannerService plannerService;
    private final RestClient.Builder builder;

    public PlannerController(PlannerService plannerService, RestClient.Builder builder) {
        this.plannerService = plannerService;
        this.builder = builder;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Map<String, Object>> createPlanner(@RequestBody CreatePlannerRequestDTO createPlannerRequestDTO) {
        UUID id = plannerService.createPlanner(createPlannerRequestDTO);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("id", id);
        requestMap.put("message", id != null? "플래너 생성 성공" : "그룹 생성 실패");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 조회
    @GetMapping(value = "/list/{user_id}")
    public ResponseEntity<?> getTodoPlannerByUserid(@PathVariable String user_id) {
        return ResponseEntity.ok(plannerService.getAllPlannersByUserId(user_id));
    }

//    상세 조회
    @GetMapping(value = "/list/{user_id}/{name}")
    public ResponseEntity<?> getPlannerByUserIdAndName(@PathVariable String user_id, @PathVariable String name) {
        return ResponseEntity.ok(plannerService.getPlannerByUserIdAndName(user_id, name));
    }
}