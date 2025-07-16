package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import gitpumta.gitpumta.timer.domain.TimerDAO;
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

    public String getMyMonitoringGroup(UUID groupId) {
        GroupDAO group = groupDAORepository.findByIdAndDeletedAtIsNull(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));

        return group.getName();
    }

    // 그룹 설명 가져오기 -> 그룹 테이블 검색
    public String getMonitoringGroupDescription(UUID groupId) {
        GroupDAO group = groupDAORepository.findByIdAndDeletedAtIsNull(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));

        return group.getDescription();
    }

    // 그룹 내 나의 랭킹 계산 -> 그룹 테이블 + 타이머 테이블 조인
    public int getMyRank(UUID accountId, UUID groupId) {/*
        // groupId로 같은 그룹 속한 userId 리스트 가져오기
        List<UUID> userIds = groupMemberDAORepository
                .findAllByGroupIdAndDeletedAtIsNull(groupId)
                .stream()
                .map(GroupMemberDAO::getUserId)
                .toList();

        // 해당 userId 리스트에 대해 TimerDAO에서 totalDuration 내림차순 정렬
        List<TimerDAO> timers = timerDAORepository
                .findByUserIdInAndDeletedAtIsNullOrderByTotalDurationDesc(userIds);

        // 정렬된 리스트에서 해당 유저 랭킹 찾기
        for (int i = 0; i < timers.size(); i++) {
            if (timers.get(i).getUserId().equals(accountId)) {
                return i + 1;
            }
        }*/

        return 0; // 해당 멤버 없을 때
    }

    // 내 이름 가져오기 -> 유저 테이블 참조
    public String getMyName(UUID accountId) {
        UserDAO user = userDAORepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 아이디입니다."));

        return user.getNickname();
    }

    // 그룹 내 공부 시간 랭킹 리스트 -> 그룹 테이블 + 타이머 테이블 조인
    public List<GetMemberTimersResponseDTO.DurationLeaderDTO> getDurationLeaders(UUID accountId, UUID groupId) {


        return null;
    }

    // 그룹 내 커밋 개수 랭킹 리스트 -> 그룹 테이블 + 커밋 테이블 조인
    public List<GetMemberTimersResponseDTO.CommitLeaderDTO> getCommitLeaders(UUID accountId, UUID groupId) {


        return null;
    }
}
