package gitpumta.gitpumta.user.bean;

import gitpumta.gitpumta.user.domain.dto.GetUserRequestDTO;
import gitpumta.gitpumta.user.domain.dto.GetUserResponseDTO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.stereotype.Component;

@Component
public class GetuserBean {
    private final UserDAORepository userDAORepository;
    public GetuserBean(UserDAORepository userDAORepository) {
        this.userDAORepository = userDAORepository;
    }

    public GetUserResponseDTO exec(GetUserRequestDTO getUserRequestDTO) {
        return userDAORepository.findById(getUserRequestDTO.getUserId());
    }
}
