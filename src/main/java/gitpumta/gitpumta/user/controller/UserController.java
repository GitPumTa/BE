package gitpumta.gitpumta.user.controller;
import gitpumta.gitpumta.user.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
