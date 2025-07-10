package gitpumta.gitpumta.user.controller;
import java.util.*;

import gitpumta.gitpumta.user.domain.dto.GetUserRequestDTO;
import gitpumta.gitpumta.user.domain.dto.GetUserResponseDTO;
import gitpumta.gitpumta.user.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
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

    @GetMapping(value = "/get")
    public ResponseEntity<Map<String, Object>> getUser(@RequestParam GetUserRequestDTO getUserRequestDTO) {
        GetUserResponseDTO getUserResponseDTO = userService.getUser(getUserRequestDTO);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("is_success", getUserResponseDTO != null);
        requestMap.put("message", getUserResponseDTO != null ? "유저 정보 반환 성공. " : "유저 정보 반환 실패. ");
        requestMap.put("user", getUserResponseDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
