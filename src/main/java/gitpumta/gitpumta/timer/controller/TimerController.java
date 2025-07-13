package gitpumta.gitpumta.timer.controller;

import gitpumta.gitpumta.timer.domain.dto.StartTimerRequestDTO;
import gitpumta.gitpumta.timer.domain.dto.StopTimerRequestDTO;
import gitpumta.gitpumta.timer.service.TimerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/Main/timer")
@CrossOrigin
public class TimerController {
    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

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
}
