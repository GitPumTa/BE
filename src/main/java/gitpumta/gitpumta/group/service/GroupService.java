package gitpumta.gitpumta.group.service;

import gitpumta.gitpumta.group.bean.CreateGroupBean;
import gitpumta.gitpumta.group.bean.JoinGroupBean;
import gitpumta.gitpumta.group.bean.small.GetGroupMembersBean;
import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.GroupMemberDAO;
import gitpumta.gitpumta.group.domain.dto.*;
import gitpumta.gitpumta.group.repository.GroupMemberDAORepository;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// 그룹 조회 목록 기능 import
import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

@Service
public class GroupService {

    private final CreateGroupBean createGroupBean;
    private final GroupDAORepository groupRepository;
    private final JoinGroupBean joinGroupBean;
    private final PasswordEncoder passwordEncoder;
    private final GroupMemberDAORepository groupMemberDAORepository;
    private final GetGroupMembersBean getGroupMembersBean;
    private final UserDAORepository userDAORepository;

    public GroupService(CreateGroupBean createGroupBean,
                        GroupDAORepository groupRepository,
                        JoinGroupBean joinGroupBean,
                        PasswordEncoder passwordEncoder,
                        GroupMemberDAORepository groupMemberDAORepository,
                        GetGroupMembersBean getGroupMembersBean,
                        UserDAORepository userDAORepository) {
        this.createGroupBean = createGroupBean;
        this.groupRepository = groupRepository;
        this.joinGroupBean = joinGroupBean;
        this.passwordEncoder = passwordEncoder;
        this.groupMemberDAORepository = groupMemberDAORepository;
        this.getGroupMembersBean = getGroupMembersBean;
        this.userDAORepository = userDAORepository;
    }

    // 그룹 생성
    public UUID createGroup(CreateGroupRequestDTO createGroupRequestDTO) {

        // 그룹 생성
        GroupDAO groupDAO = createGroupBean.exec(createGroupRequestDTO);
        groupDAO.setCreatedAt(LocalDateTime.now());
        groupRepository.save(groupDAO);

        // 그룹 생성자(본인)를 멤버로 자동 등록
        UserDAO user = userDAORepository.findById(createGroupRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        GroupMemberDAO member = GroupMemberDAO.builder()
                .id(UUID.randomUUID())
                .groupId(groupDAO.getId())
                .userId(user.getId())
                .joinedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        groupMemberDAORepository.save(member);

        return groupDAO.getId();
    }

    // 그룹 목록 조회 메소드
    public List<GroupListDTO> getAllGroups() {
        return groupRepository.findByDeletedAtIsNull()
                .stream()
                .map(group -> {
                    int memberCnt = groupMemberDAORepository.countByGroupIdAndDeletedAtIsNull(group.getId());

                    return GroupListDTO.builder()
                            .id(group.getId())
                            .name(group.getName())
                            .description(group.getDescription())
                            .capacity(group.getCapacity())
                            .memberCnt(memberCnt) // ← 실제 카운트 반영
                            .build();
                })
                .collect(Collectors.toList());
    }

    // 그룹명 또는 설명에 사용자가 입력한 키워드를 포함하는 그룹 검색
    public List<GroupResponseDTO> searchGroups(String keyword) {
        return groupRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeletedAtIsNull(keyword, keyword)
                .stream()
                .map(group -> GroupResponseDTO.builder()
                        .id(group.getId())
                        .name(group.getName())
                        .rule(group.getRule())
                        .description(group.getDescription())
                        .capacity(group.getCapacity())
                        .build())
                .collect(Collectors.toList());
    }

    // 그룹 상세 정보 조회
    public GroupResponseDTO getGroupDetail(UUID groupId) {
        GroupDAO group = groupRepository.findByIdAndDeletedAtIsNull(groupId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 그룹"));

        int memberCnt = groupMemberDAORepository.countByGroupIdAndDeletedAtIsNull(groupId);

        return GroupResponseDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .capacity(group.getCapacity())
                .rule(group.getRule())
                .memberCnt(memberCnt)
                .build();
    }

    // UUID 반영 가입 처리 로직
    public GroupMemberDAO joinGroup(UUID groupId, String inputPassword, UUID userId) {
        GroupDAO group = groupRepository.findByIdAndDeletedAtIsNull(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다.")); // controller에서 catch
        if (!joinGroupBean.exec(inputPassword, group.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // 가입 정원 체크 로직
        int currentCount = groupMemberDAORepository.findByGroupIdAndDeletedAtIsNull(groupId).size();
        int capacity = Optional.ofNullable(group.getCapacity()).orElse(Integer.MAX_VALUE);

        if (currentCount >= capacity) {
            throw new IllegalStateException("정원이 초과되어 가입할 수 없습니다.");
        }

        // 이미 가입한 경우 방지
        if (groupMemberDAORepository.existsByGroupIdAndUserIdAndDeletedAtIsNull(groupId, userId)) {
            throw new IllegalStateException("이미 가입한 그룹입니다.");
        }

        // 가입 처리
        GroupMemberDAO member = GroupMemberDAO.builder()
                .id(UUID.randomUUID())
                .groupId(groupId)
                .userId(userId)
                .joinedAt(LocalDateTime.now())
                .build();

        groupMemberDAORepository.save(member);

        return member;
    }

    // 그룹 정보 수정 (이름/설명/비번/규칙)
    public void updateGroup(UpdateGroupRequestDTO dto) {
        GroupDAO group = groupRepository.findByIdAndDeletedAtIsNull(dto.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            group.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            group.setDescription(dto.getDescription());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            group.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getCapacity() != null && dto.getCapacity() > 0) {
            group.setCapacity(dto.getCapacity());
        }
        if (dto.getRule() != null) {
            group.setRule(dto.getRule());
        }

        int currentMemberCnt = groupMemberDAORepository.countByGroupIdAndDeletedAtIsNull(group.getId());
        group.setMemberCnt(currentMemberCnt);

        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.save(group);
    }

    // 그룹에 가입된 맴버 조회
    public List<GroupMemberSimpleDTO> getGroupMembers(UUID groupId) {
        return getGroupMembersBean.exec(groupId).stream()
                .map(GroupMemberSimpleDTO::new)
                .collect(Collectors.toList());
    }

    // 그룹장이 맴버 강제 추방
    public Map<String, Object> expelMember(UUID groupId, UUID requesterId, UUID targetUserId) {
        // 그룹 조회
        GroupDAO group = groupRepository.findByIdAndDeletedAtIsNull(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹"));

        // 그룹 생성자 여부 확인
        Optional<GroupMemberDAO> firstMemberOpt = groupMemberDAORepository
                .findFirstByGroupIdAndDeletedAtIsNullOrderByJoinedAtAsc(groupId);

        boolean isLeader = firstMemberOpt
                .map(member -> member.getUserId().equals(requesterId))
                .orElse(false);


        if (requesterId.equals(targetUserId)) {
            throw new IllegalArgumentException("자기 자신을 추방할 수 없음");
        }

        if (!isLeader) {
            throw new IllegalArgumentException("강제 추방 기능은 그룹장만 사용 가능");
        }

        // 대상 유저가 멤버인지 확인
        GroupMemberDAO target = groupMemberDAORepository.findByGroupIdAndUserIdAndDeletedAtIsNull(groupId, targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("이 유저는 그룹의 맴버가 아님"));

        target.setDeletedAt(LocalDateTime.now());
        groupMemberDAORepository.save(target);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "멤버 추방 완료");
        response.put("expelledUserId", targetUserId);
        return response;
    }

    // 맴버 스스로 그룹 탈퇴
    public Map<String, Object> leaveGroup(UUID groupId, UUID userId) {
        GroupMemberDAO member = groupMemberDAORepository.findByGroupIdAndUserIdAndDeletedAtIsNull(groupId, userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 그룹에 속해 있지 않습니다."));

        member.setDeletedAt(LocalDateTime.now());
        groupMemberDAORepository.save(member);

        Map<String, Object> result = new HashMap<>();
        result.put("message", "그룹 탈퇴 완료");
        result.put("leftUserId", userId);
        result.put("groupId", groupId);
        return result;
    }
}