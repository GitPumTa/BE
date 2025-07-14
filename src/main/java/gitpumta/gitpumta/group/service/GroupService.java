package gitpumta.gitpumta.group.service;

import gitpumta.gitpumta.group.bean.CreateGroupBean;
import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import org.springframework.stereotype.Service;

// 그룹 조회 목록 기능 import
import gitpumta.gitpumta.group.domain.dto.GroupResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GroupService {

    private final CreateGroupBean createGroupBean;
    private final GroupDAORepository groupRepository;

    public GroupService(CreateGroupBean createGroupBean, GroupDAORepository groupRepository) {
        this.createGroupBean = createGroupBean;
        this.groupRepository = groupRepository;
    }

    public UUID createGroup(CreateGroupRequestDTO createGroupRequestDTO) {
        GroupDAO groupDAO = createGroupBean.exec(createGroupRequestDTO);
        groupDAO.setCreatedAt(LocalDateTime.now());
        groupRepository.save(groupDAO);
        return groupDAO.getId();
    }

    // 그룹 목록 조회 메소드
    public List<GroupResponseDTO> getAllGroups() {
        return groupRepository.findByDeletedAtIsNull()
                .stream()
                .map(group -> GroupResponseDTO.builder()
                        .id(group.getId())
                        .name(group.getName())
                        .description(group.getDescription())
                        .capacity(group.getCapacity())
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
                        .description(group.getDescription())
                        .capacity(group.getCapacity())
                        .build())
                .collect(Collectors.toList());
    }
}