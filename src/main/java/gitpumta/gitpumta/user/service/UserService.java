package gitpumta.gitpumta.user.service;

import gitpumta.gitpumta.planner.domain.dto.PlannerResponseDTO;
import gitpumta.gitpumta.user.bean.GetuserBean;
import gitpumta.gitpumta.user.bean.LoginBean;
import gitpumta.gitpumta.user.bean.SignupBean;
import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.GetUserRequestDTO;
import gitpumta.gitpumta.user.domain.dto.GetUserResponseDTO;
import gitpumta.gitpumta.user.domain.dto.RankingResponseDTO;
import gitpumta.gitpumta.user.domain.dto.UserRatingInfoDTO;
import gitpumta.gitpumta.user.domain.dto.LoginUserRequestDTO;
import gitpumta.gitpumta.user.domain.dto.SignUpUserDTO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDAORepository userDAORepository;
    private final GetuserBean getuserBean;
    private final LoginBean loginBean;
    private final SignupBean signupBean;

    public GetUserResponseDTO getUser(UUID id) {
        UserDAO user = userDAORepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        return GetUserResponseDTO.builder()
                .nickname(user.getNickname())
                .gitId(user.getGitId())
                .createdAt(user.getCreatedAt())
                .build();
    }
  
    public UserService(UserDAORepository userDAORepository , GetuserBean getuserBean, LoginBean loginBean,
                       SignupBean signupBean) {
        this.userDAORepository = userDAORepository;
        this.getuserBean = getuserBean;
        this.loginBean = loginBean;
        this.signupBean = signupBean;
    }

    // 입력받은 id password로 password 해시값과 비교해서 확인하기.
    public UserDAO login(LoginUserRequestDTO loginUserRequestDTO) {
        return loginBean.exec(loginUserRequestDTO);
    }

    public UserDAO signup(SignUpUserDTO signUpUserDTO) {
        return signupBean.exec(signUpUserDTO);
    }
}
