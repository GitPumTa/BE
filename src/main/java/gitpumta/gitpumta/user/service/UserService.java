package gitpumta.gitpumta.user.service;

import gitpumta.gitpumta.planner.domain.dto.PlannerResponseDTO;
import gitpumta.gitpumta.user.bean.GetuserBean;
import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.GetUserRequestDTO;
import gitpumta.gitpumta.user.domain.dto.GetUserResponseDTO;
import gitpumta.gitpumta.user.domain.dto.RankingResponseDTO;
import gitpumta.gitpumta.user.domain.dto.UserRatingInfoDTO;
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

//    public UserService(UserDAORepository userDAORepository , GetuserBean getuserBean) {
//        this.userDAORepository = userDAORepository;
//        this.getuserBean = getuserBean;
//    }

    public GetUserResponseDTO getUser(UUID id) {
        UserDAO user = userDAORepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        return GetUserResponseDTO.builder()
                .nickname(user.getNickname())
                .gitId(user.getGitId())
                .createdAt(user.getCreatedAt())
                .build();
    }
//    // 조회 메서드
//    public List<UUID> getUserId(UUID userId) {
//        List<UserDAO> userDAOS = userDAORepository.findByuserIdAndDeletedAtIsNull(userId);
//
//        return userId.stream()
//                .map(userId -> userId.getUser().getId())  // UserDAO → UUID
//                .collect(Collectors.toList());
//    }
}
