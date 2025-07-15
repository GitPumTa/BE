package gitpumta.gitpumta.user.bean;

import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.LoginUserRequestDTO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginBean {
    private UserDAORepository userDAORepository;
    private PasswordEncoder passwordEncoder;
    public LoginBean(UserDAORepository userDAORepository, PasswordEncoder passwordEncoder) {
        this.userDAORepository = userDAORepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserDAO exec(LoginUserRequestDTO loginUserRequestDTO){
        Optional<UserDAO> user = userDAORepository.findByAccountId(loginUserRequestDTO.getAccountId());
        boolean check = passwordEncoder.matches(loginUserRequestDTO.getPassword(), user.get().getPassword());
        if(check){
            return user.get();
        }else {
            return null;
        }
    }
}
