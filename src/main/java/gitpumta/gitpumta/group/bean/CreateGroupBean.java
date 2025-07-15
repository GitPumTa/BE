package gitpumta.gitpumta.group.bean;

import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.domain.dto.GroupResponseDTO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateGroupBean {
    private GroupDAORepository repository;
    public CreateGroupBean(GroupDAORepository repository) {
        this.repository = repository;
    }
    public GroupDAO exec(CreateGroupRequestDTO createGroupRequestDTO){
        GroupDAO group = GroupDAO.builder()
                .id(UUID.randomUUID())
                .name(createGroupRequestDTO.getName())
                .password(createGroupRequestDTO.getPassword())
                .capacity(createGroupRequestDTO.getCapacity())
                .description(createGroupRequestDTO.getDescription())
                .build();
        return repository.save(group);
    }
}
