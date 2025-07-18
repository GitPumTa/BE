package gitpumta.gitpumta.timer.bean;

import gitpumta.gitpumta.commit.repository.CommitDAORepository;
import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.GroupMemberDAO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import gitpumta.gitpumta.group.repository.GroupMemberDAORepository;
import gitpumta.gitpumta.planner.domain.PlannerDAO;
import gitpumta.gitpumta.planner.repository.PlannerDAORepository;
import gitpumta.gitpumta.timer.domain.TimerDAO;
import gitpumta.gitpumta.timer.domain.dto.GetMemberTimersResponseDTO;
import gitpumta.gitpumta.timer.repository.TimerDAORepository;
import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MemberTimersBean {
    private final TimerDAORepository timerDAORepository;
    private final GroupDAORepository groupDAORepository;
    private final UserDAORepository userDAORepository;
    private final CommitDAORepository commitDAORepository;
    private final GroupMemberDAORepository groupMemberDAORepository;

    public MemberTimersBean(TimerDAORepository timerDAORepository, GroupDAORepository groupDAORepository,
                            UserDAORepository userDAORepository, CommitDAORepository commitDAORepository,
                            GroupMemberDAORepository groupMemberDAORepository) {
        this.timerDAORepository = timerDAORepository;
        this.groupDAORepository = groupDAORepository;
        this.userDAORepository = userDAORepository;
        this.commitDAORepository = commitDAORepository;
        this.groupMemberDAORepository = groupMemberDAORepository;
    }

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

    // 그룹 내 나의 랭킹(커밋 수 기준) 계산
    public int getMyRank(UUID accountId, UUID groupId) {
        // groupId로 같은 그룹 속한 userId 리스트 가져오기
        List<UUID> userIds = groupMemberDAORepository
                .findAllByGroupIdAndDeletedAtIsNull(groupId)
                .stream()
                .map(GroupMemberDAO::getUserId)
                .toList();

        // 해당 userId 리스트에 대해 TimerDAO에서 totalDuration 내림차순 정렬
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        Map<UUID, Integer> commitCountMap = new HashMap<>();
        for (UUID userId : userIds) {
            int count = commitDAORepository
                    .countByUserIdAndTimeBetweenAndDeletedAtIsNull(userId, startOfDay, endOfDay);
            commitCountMap.put(userId, count);
        }

        List<Map.Entry<UUID, Integer>> sorted = commitCountMap.entrySet().stream()
                .sorted(Map.Entry.<UUID, Integer>comparingByValue().reversed())
                .toList();

        int rank = 1;
        for (Map.Entry<UUID, Integer> e : sorted) {
            if (e.getKey().equals(accountId)) {
                return rank;
            }
            rank++;
        }

        // 그룹에 속해 있지만 커밋 기록이 없으면 0 반환
        return 0;
    }

    // 내 이름 가져오기 -> 유저 테이블 참조
    public String getMyName(UUID accountId) {
        UserDAO user = userDAORepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 아이디입니다."));

        return user.getNickname();
    }

    // 그룹 내 공부 시간 랭킹 리스트
    public List<GetMemberTimersResponseDTO.DurationLeaderDTO> getDurationLeaders(UUID groupId) {
        // groupId로 같은 그룹 속한 userId 리스트 가져오기
        List<UUID> userIds = groupMemberDAORepository
                .findAllByGroupIdAndDeletedAtIsNull(groupId)
                .stream()
                .map(GroupMemberDAO::getUserId)
                .toList();

        // 해당 userId 리스트에 대해 TimerDAO에서 totalDuration 내림차순 정렬
        List<TimerDAO> timers = timerDAORepository
                .findByUserIdInAndDeletedAtIsNullOrderByTotalDurationDesc(userIds);

        // user name(nickname) 정보 매핑
        Map<UUID, String> userNicknameMap = userDAORepository
                .findAllById(userIds)
                .stream()
                .collect(Collectors.toMap(UserDAO::getId, UserDAO::getNickname));

        // DurationLeaderDTO 리스트로 매핑 후 반환
        List<GetMemberTimersResponseDTO.DurationLeaderDTO> result = new ArrayList<>();
        int rank = 1;

        for (TimerDAO timer : timers) {
            var dto = new GetMemberTimersResponseDTO.DurationLeaderDTO();
            dto.setName(userNicknameMap.get(timer.getUserId()));
            dto.setDuration(timer.getTotalDuration());
            dto.setStatus(Integer.parseInt(timer.getStatus()));
            dto.setSendAt(timer.getUpdatedAt());
            dto.setRank(rank++);
            result.add(dto);
        }

        return result;
    }

    // 그룹 내 커밋 개수 랭킹 리스트
    public List<GetMemberTimersResponseDTO.CommitLeaderDTO> getCommitLeaders(UUID groupId) {
        // groupId로 같은 그룹 속한 userId 리스트 가져오기
        List<UUID> userIds = groupMemberDAORepository
                .findAllByGroupIdAndDeletedAtIsNull(groupId)
                .stream()
                .map(GroupMemberDAO::getUserId)
                .toList();

        // 유저 별 모든 플래너 -> 플래너에 연결된 커밋 검색(그중에서 오늘자)
        // userId, 닉네임 매핑
        Map<UUID, String> nicknameMap = userDAORepository
                .findAllById(userIds)
                .stream()
                .collect(Collectors.toMap(UserDAO::getId, UserDAO::getNickname));

        // 오늘 날짜
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        // userId → 커밋 갯수 맵
        Map<UUID, Integer> commitCountMap = new HashMap<>();

        for (UUID userId : userIds) {
            int totalCommits = commitDAORepository.countByUserIdAndTimeBetweenAndDeletedAtIsNull(
                    userId, startOfDay, endOfDay);

            commitCountMap.put(userId, totalCommits);
        }

        // DTO 리스트 + 정렬 + 랭크
        List<GetMemberTimersResponseDTO.CommitLeaderDTO> result = new ArrayList<>();

        commitCountMap.entrySet().stream()
                .sorted(Map.Entry.<UUID, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> {
                    GetMemberTimersResponseDTO.CommitLeaderDTO dto = new GetMemberTimersResponseDTO.CommitLeaderDTO();
                    dto.setName(nicknameMap.get(entry.getKey()));
                    dto.setCommitCount(entry.getValue());
                    result.add(dto);
                });

        // 랭크 부여
        int rank = 1;
        for (GetMemberTimersResponseDTO.CommitLeaderDTO dto : result) {
            dto.setRank(rank++);
        }

        return result;
    }
}
