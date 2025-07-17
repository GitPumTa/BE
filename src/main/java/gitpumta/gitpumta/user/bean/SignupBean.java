package gitpumta.gitpumta.user.bean;

import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.SignUpUserDTO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SignupBean {
    private final PasswordEncoder passwordEncoder;
    private final UserDAORepository userDAORepository;
    public SignupBean(PasswordEncoder passwordEncoder, UserDAORepository userDAORepository) {
        this.passwordEncoder = passwordEncoder;
        this.userDAORepository = userDAORepository;
    }
    public UserDAO exec(SignUpUserDTO signUpUserDTO) {
        UserDAO userDAO = userDAORepository.findByAccountId(signUpUserDTO.getAccountId());
        if (userDAO != null)    return null;
        userDAO = UserDAO.builder()
                .id(UUID.randomUUID())
                .accountId(signUpUserDTO.getAccountId())
                .password(passwordEncoder.encode(signUpUserDTO.getPassword()))
                .nickname(signUpUserDTO.getNickname())
                .gitId(signUpUserDTO.getGitId()!= null?signUpUserDTO.getGitId():"")
                .build();
        userDAORepository.save(userDAO);
        return userDAO;
    }
}
