package gitpumta.gitpumta.user.bean;

import gitpumta.gitpumta.user.domain.UserDAO;
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
//        GetUserResponseDTO getUserResponseDTO = new GetUserResponseDTO();
//        UserDAO userDAO = userDAORepository.getOne(getUserRequestDTO.getUserId());
//        getUserResponseDTO.setId(userDAO.getId());
//        getUserResponseDTO.setNickname(userDAO.getNickname());
//        getUserResponseDTO.setAccountId(userDAO.getAccountId());
//        getUserResponseDTO.setPassword(userDAO.getPassword());
//        getUserResponseDTO.setGitId(userDAO.getGitId());
//        getUserResponseDTO.setCreatedAt(userDAO.getCreatedAt());
//        getUserResponseDTO.setUpdatedAt(userDAO.getUpdatedAt());
//        getUserResponseDTO.setDeletedAt(userDAO.getDeletedAt());
//        return getUserResponseDTO;
        return null;
    }
}
