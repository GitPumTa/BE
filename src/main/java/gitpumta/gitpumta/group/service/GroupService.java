package gitpumta.gitpumta.group.service;

import gitpumta.gitpumta.group.bean.CreateGroupBean;
import gitpumta.gitpumta.group.bean.JoinGroupBean;
import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.domain.dto.UpdateGroupRequestDTO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import gitpumta.gitpumta.group.domain.dto.GroupListDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// 그룹 조회 목록 기능 import
import gitpumta.gitpumta.group.domain.dto.GroupResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;

@Service
public class GroupService {

    private final CreateGroupBean createGroupBean;
    private final GroupDAORepository groupRepository;
    private final JoinGroupBean joinGroupBean;
    private final PasswordEncoder passwordEncoder;

    public GroupService(CreateGroupBean createGroupBean,
                        GroupDAORepository groupRepository,
                        JoinGroupBean joinGroupBean,
                        PasswordEncoder passwordEncoder ) {
        this.createGroupBean = createGroupBean;
        this.groupRepository = groupRepository;
        this.joinGroupBean = joinGroupBean;
        this.passwordEncoder = passwordEncoder;
    }

    // 그룹 생성
    public UUID createGroup(CreateGroupRequestDTO createGroupRequestDTO) {
        GroupDAO groupDAO = createGroupBean.exec(createGroupRequestDTO);
        groupDAO.setCreatedAt(LocalDateTime.now());
        groupRepository.save(groupDAO);
        return groupDAO.getId();
    }

    // 그룹 목록 조회 메소드
    public List<GroupListDTO> getAllGroups() {
        return groupRepository.findByDeletedAtIsNull()
                .stream()
                .map(group -> GroupListDTO.builder()
                        .id(group.getId())
                        .name(group.getName())
                        .description(group.getDescription())
                        .capacity(group.getCapacity())
                        .memberCnt(0) // 가입 인원은 아직 기능 미구현이므로 0
                        .build())
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

        int memberCnt = 0;

        return GroupResponseDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .capacity(group.getCapacity())
                .rule(group.getRule())
                .memberCnt(memberCnt)
                .build();
    }

    // 그룹 가입
    public void joinGroup(UUID groupId, String inputPassword) {
        GroupDAO group = groupRepository.findByIdAndDeletedAtIsNull(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다..."));
        // 비밀번호 검증 구현부
        if (!joinGroupBean.exec(inputPassword, group.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        int currentMembers = Optional.ofNullable(group.getMemberCnt()).orElse(0);
        int capacity = group.getCapacity() != null ? group.getCapacity() : Integer.MAX_VALUE;

        if (currentMembers >= capacity) {
            throw new IllegalStateException("정원 초과로 인한 가입 거절");
        }

        group.setMemberCnt(currentMembers + 1);
        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.save(group);
    }

    // 그룹 정보 수정 (이름/설명/비번/규칙)
    public void updateGroup(UpdateGroupRequestDTO dto) {
        GroupDAO group = groupRepository.findByIdAndDeletedAtIsNull(dto.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));

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

        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.save(group);
    }
}