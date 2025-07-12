package gitpumta.gitpumta.group.service;

import gitpumta.gitpumta.group.bean.CreateGroupBean;
import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import org.springframework.stereotype.Service;

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
}