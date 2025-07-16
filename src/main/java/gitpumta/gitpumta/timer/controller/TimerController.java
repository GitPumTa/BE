package gitpumta.gitpumta.timer.controller;

import gitpumta.gitpumta.timer.domain.dto.GetMemberTimersResponseDTO;
import gitpumta.gitpumta.timer.domain.dto.MainTimerResponseDTO;
import gitpumta.gitpumta.timer.domain.dto.TimerRequestDTO;
import gitpumta.gitpumta.timer.service.TimerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/main")
@CrossOrigin
public class TimerController {
    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMainTimer(@RequestParam("account_id") UUID accountId) {
        Map<String, Object> responseMap = new HashMap<>();

        try {
            MainTimerResponseDTO mainTimerResponseDTO = timerService.getMain(accountId);

            responseMap.put("message", "정상적으로 repo list 를 로드하였습니다.");
            responseMap.put("repos", mainTimerResponseDTO.getRepos());

            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            responseMap.put("message", "repo list를 로드하는데 실패했습니다.");
            responseMap.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }

    @PostMapping(value = "/timer")
    public ResponseEntity<Map<String, Object>> handleTimer(@RequestBody TimerRequestDTO requestDTO) {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            LocalDateTime initialStart;

            switch (requestDTO.getStatus()) {
                case 0 -> {
                    initialStart = timerService.startTimer(requestDTO);
                    responseMap.put("message", "정상적으로 timer 를 시작하였습니다.");
                }
                case 1 -> {
                    initialStart = timerService.stopTimer(requestDTO);
                    responseMap.put("message", "timer 를 정지하였습니다.");
                }
                default -> {
                    responseMap.put("message", "잘못된 status 정보입니다.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
                }
            }
            responseMap.put("initial_start", initialStart);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            responseMap.put("message", "timer 처리 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }

    @GetMapping(value = "/group")
    public ResponseEntity<Map<String, Object>> getLeaders(@RequestParam("account_id") UUID accountId,
                                                          @RequestParam("group_id") UUID groupId) {
        Map<String, Object> responseMap = new HashMap<>();

        try {
            GetMemberTimersResponseDTO getMemberTimersResponseDTO = timerService.getMemberTimers(accountId, groupId);

            responseMap.put("message", "정상적으로 group ranking 을 로드하였습니다.");
            responseMap.put("my_monitoring_group", getMemberTimersResponseDTO.getMyMonitoringGroup());
            responseMap.put("my_monitoring_group_description", getMemberTimersResponseDTO.getMyMonitoringGroupDescription());
            responseMap.put("my_rank", getMemberTimersResponseDTO.getMyRank());
            responseMap.put("my_name", getMemberTimersResponseDTO.getMyName());
            responseMap.put("duration_leaders", getMemberTimersResponseDTO.getDurationLeaders());
            responseMap.put("commit_leaders", getMemberTimersResponseDTO.getCommitLeaders());

            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            responseMap.put("message", "group timer 확인 실패");
            responseMap.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }
}
