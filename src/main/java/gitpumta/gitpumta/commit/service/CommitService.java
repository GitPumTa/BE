package gitpumta.gitpumta.commit.service;

import gitpumta.gitpumta.commit.repository.CommitDAORepository;
import org.springframework.stereotype.Service;

@Service
public class CommitService {
    private final CommitDAORepository commitDAORepository;

    public CommitService(CommitDAORepository commitDAORepository) {
        this.commitDAORepository = commitDAORepository;
    }
}
