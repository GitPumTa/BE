package gitpumta.gitpumta.group.bean.small;

import gitpumta.gitpumta.group.domain.GroupMemberDAO;
import gitpumta.gitpumta.group.repository.GroupMemberDAORepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetGroupMembersBean {

    private final GroupMemberDAORepository repository;
    public GetGroupMembersBean(GroupMemberDAORepository repository) {
        this.repository = repository;
    }
    public List<GroupMemberDAO> exec(UUID groupId) {
        return repository.findByGroupIdAndDeletedAtIsNull(groupId);
    }
}
