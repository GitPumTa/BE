package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import gitpumta.gitpumta.timer.domain.dto.GetMemberTimersResponseDTO;
import gitpumta.gitpumta.timer.repository.TimerDAORepository;
import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MemberTimersBean {
    private final TimerDAORepository timerDAORepository;
    private final GroupDAORepository groupDAORepository;
    private final UserDAORepository userDAORepository;

    public MemberTimersBean(TimerDAORepository timerDAORepository, GroupDAORepository groupDAORepository,
                            UserDAORepository userDAORepository) {
        this.timerDAORepository = timerDAORepository;
        this.groupDAORepository = groupDAORepository;
        this.userDAORepository = userDAORepository;
    }

    //public GetMemberTimersResponseDTO exec(GetMemberTimersRequestDTO getMemberTimersRequestDTO) {
    //}

    // 그룹 이름 가져오기 -> 그룹 테이블 검색
    public String getMyMonitoringGroup(UUID groupId) {
        GroupDAO group = groupDAORepository.findByNameDeletedAtIsNull(groupId);

        return group.getName();
    }

    // 그룹 설명 가져오기 -> 그룹 테이블 검색
    public String getMonitoringGroupDescription(UUID groupId) {
        GroupDAO group = groupDAORepository.findByGroupDescriptionDeletedAtIsNull(groupId);

        return group.getDescription();
    }

    // 그룹 내 나의 랭킹 계산 -> 그룹 테이블 + 타이머 테이블 조인
    public int getMyRank(String accountId, UUID groupId) {
        return 0;
    }

    // 내 이름 가져오기 -> 유저 테이블 참조
    public String getMyName(String accountId) {
        UserDAO user = userDAORepository.findByAccountId(accountId);

        return user.getNickname();
    }

    // 그룹 내 공부 시간 랭킹 리스트 -> 그룹 테이블 + 타이머 테이블 조인
    public List<GetMemberTimersResponseDTO.DurationLeaderDTO> getDurationLeaders(String accountId, UUID groupId) {
        return null;
    }

    // 그룹 내 커밋 개수 랭킹 리스트 -> 그룹 테이블 + 커밋 테이블 조인
    public List<GetMemberTimersResponseDTO.CommitLeaderDTO> getCommitLeaders(String accountId, UUID groupId) {
        return null;
    }
}
