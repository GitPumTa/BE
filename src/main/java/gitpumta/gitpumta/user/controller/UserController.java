package gitpumta.gitpumta.user.controller;
import java.util.*;

import ch.qos.logback.classic.Logger;
import gitpumta.gitpumta.user.domain.dto.GetUserResponseDTO;
import gitpumta.gitpumta.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/test")
    public ResponseEntity<Map<String, Object>> test(){
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("is_success", true);
        requestMap.put("message", "테스트 성공");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

//    @GetMapping(value = "/get")
//    public ResponseEntity<Map<String, Object>> getUser(@RequestBody GetUserRequestDTO getUserRequestDTO) {
//        GetUserResponseDTO getUserResponseDTO = userService.getUser(getUserRequestDTO);
//        Map<String, Object> requestMap = new HashMap<>();
//        requestMap.put("is_success", getUserResponseDTO != null);
//        requestMap.put("message", getUserResponseDTO != null ? "유저 정보 반환 성공. " : "유저 정보 반환 실패. ");
//        requestMap.put("user", getUserResponseDTO);
//
//        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
//    }

    // 조회
    @GetMapping("/api/users/{id}")
    public ResponseEntity<GetUserResponseDTO> getUser(@PathVariable UUID id) {
        log.info("조회 요청 UUID: {}", id);
        GetUserResponseDTO response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }
}
