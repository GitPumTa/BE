package gitpumta.gitpumta.group.bean;

import gitpumta.gitpumta.group.bean.small.PasswordEncoderBean;
import gitpumta.gitpumta.group.domain.GroupDAO;
import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.domain.dto.GroupResponseDTO;
import gitpumta.gitpumta.group.repository.GroupDAORepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateGroupBean {
    private final GroupDAORepository repository;
    private final PasswordEncoderBean passwordEncoderBean;
    public CreateGroupBean(GroupDAORepository repository, PasswordEncoderBean passwordEncoderBean) {
        this.passwordEncoderBean = passwordEncoderBean;
        this.repository = repository;
    }
    public GroupDAO exec(CreateGroupRequestDTO createGroupRequestDTO){
        GroupDAO group = GroupDAO.builder()
                .id(UUID.randomUUID())
                .name(createGroupRequestDTO.getName())
                .rule(createGroupRequestDTO.getRule())
                .password(passwordEncoderBean.encode(createGroupRequestDTO.getPassword()))
                .capacity(createGroupRequestDTO.getCapacity())
                .description(createGroupRequestDTO.getDescription())
                .memberCnt(0)
                .build();
        return repository.save(group);
    }
}
