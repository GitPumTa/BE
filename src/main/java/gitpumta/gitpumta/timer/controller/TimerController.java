package gitpumta.gitpumta.timer.controller;

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
@RequestMapping(value = "/main/timer")
@CrossOrigin
public class TimerController {
    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> handleTimer(@RequestHeader("account_id") UUID accountId,
            @RequestBody TimerRequestDTO requestDTO) {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            LocalDateTime initialStart;

            switch (requestDTO.getStatus()) {
                case 0 -> {
                    initialStart = timerService.startTimer(accountId, requestDTO);
                    responseMap.put("message", "정상적으로 timer를 시작하였습니다.");
                }
                case 1 -> {
                    initialStart = timerService.stopTimer(accountId, requestDTO);
                    responseMap.put("message", "타이머를 정지하였습니다.");
                }
                default -> {
                    responseMap.put("message", "잘못된 상태 정보입니다.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
                }
            }
            responseMap.put("initial_start", initialStart);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            responseMap.put("message", "타이머 처리 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }
/*
    @PostMapping(value = "/start")
    public ResponseEntity<Map<String, Object>> startTimer(@RequestBody StartTimerRequestDTO startTimerRequestDTO) {
        timerService.StartTimer(startTimerRequestDTO);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "시작 저장 성공");

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @PostMapping(value = "/stop")
    public ResponseEntity<Map<String, Object>> stopTimer(@RequestBody StopTimerRequestDTO stopTimerRequestDTO) {
        try {
            timerService.StopTimer(stopTimerRequestDTO);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", stopTimerRequestDTO.getStatus().equals("0") ? "타이머 일시정지 성공" : "타이머 완전정지 성공");

            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }

    // url 매핑
    @GetMapping(value = "/")
    public ResponseEntity<Map<String, Object>> getTimers() {

    }

 */
}
