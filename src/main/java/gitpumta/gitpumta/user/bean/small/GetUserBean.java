package gitpumta.gitpumta.user.bean.small;

import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GetUserBean {
    private final UserDAORepository userDAORepository;
    public GetUserBean(UserDAORepository userDAORepository){
        this.userDAORepository = userDAORepository;
    }
    public UserDAO byId(UUID id){
        return userDAORepository.getReferenceById(id);
    }

    public List<UserDAO> byIds(List<UUID> idList){
        return userDAORepository.findAllById(idList);
    }
}
