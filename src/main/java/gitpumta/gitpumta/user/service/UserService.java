package gitpumta.gitpumta.user.service;

import gitpumta.gitpumta.user.bean.GetuserBean;
import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.GetUserRequestDTO;
import gitpumta.gitpumta.user.domain.dto.GetUserResponseDTO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAORepository userDAORepository;
    private final GetuserBean getuserBean;

    public UserService(UserDAORepository userDAORepository , GetuserBean getuserBean) {
        this.userDAORepository = userDAORepository;
        this.getuserBean = getuserBean;
    }

    public GetUserResponseDTO getUser(GetUserRequestDTO getUserRequestDTO) {
        return getuserBean.exec(getUserRequestDTO);
    }

}
