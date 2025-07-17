package gitpumta.gitpumta.user.bean;

import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.ProfileInfoDTO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProfileinfoBean {

    private final UserDAORepository userDAORepository;

    public ProfileinfoBean(UserDAORepository userDAORepository) {
        this.userDAORepository = userDAORepository;
    }

    // getUser 유저 찾기
    public UserDAO getUserById(UUID user_id) {
        return userDAORepository.findByIdAndDeletedAtIsNull(user_id)


                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자"));
    }
}
