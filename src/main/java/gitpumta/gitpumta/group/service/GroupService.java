package gitpumta.gitpumta.group.service;


import gitpumta.gitpumta.group.bean.CreateGroupBean;
import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.domain.dto.GroupResponseDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GroupService {
    private CreateGroupBean createGroupBean;
    public GroupService(CreateGroupBean createGroupBean) {
        this.createGroupBean = createGroupBean;
    }
    public UUID createGroup(CreateGroupRequestDTO createGroupRequestDTO) {
        GroupDAO groupDAO = createGroupBean.exec(createGroupRequestDTO);
        return  groupDAO.getId();
    }
}
