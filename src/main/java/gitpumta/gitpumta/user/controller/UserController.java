package gitpumta.gitpumta.user.controller;
import java.util.*;

import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.LoginUserRequestDTO;
import gitpumta.gitpumta.user.domain.dto.SignUpUserDTO;
import gitpumta.gitpumta.user.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUserRequestDTO loginUserRequestDTO){
        UserDAO userDAO = userService.login(loginUserRequestDTO);
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("is_success", userDAO!=null? "로그인 성공":"로그인 실패");
        requestMap.put("id", userDAO!=null? userDAO.getId(): "");
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody SignUpUserDTO signUpUserDTO){
        UserDAO userDAO = userService.signup(signUpUserDTO);

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("is_success", userDAO!=null? "회원가입 성공":"회원가입 실패");
        requestMap.put("id", userDAO!=null? userDAO.getId(): "");
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
