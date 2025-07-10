package gitpumta.gitpumta.user.service;

import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAORepository userDAORepository;

    public UserService(UserDAORepository userDAORepository) {
        this.userDAORepository = userDAORepository;
    }

}
