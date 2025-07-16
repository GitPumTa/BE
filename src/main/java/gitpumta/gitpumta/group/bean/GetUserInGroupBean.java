package gitpumta.gitpumta.group.bean;

import gitpumta.gitpumta.group.repository.GroupDAORepository;
import gitpumta.gitpumta.user.bean.small.GetUserBean;
import org.springframework.stereotype.Component;

@Component
public class GetUserInGroupBean {
    private final GetUserBean getUserBean;
    private final GroupDAORepository groupDAORepository;
    public GetUserInGroupBean(GetUserBean getUserBean, GroupDAORepository groupDAORepository){
        this.getUserBean = getUserBean;
        this.groupDAORepository = groupDAORepository;
    }
}
