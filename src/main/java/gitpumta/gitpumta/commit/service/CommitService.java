package gitpumta.gitpumta.commit.service;

import gitpumta.gitpumta.commit.bean.CreateCommitBean;
import gitpumta.gitpumta.commit.domain.dto.CommitListDTO;
import gitpumta.gitpumta.commit.repository.CommitDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommitService {
    private final CommitDAORepository commitDAORepository;
    private final CreateCommitBean createCommitBean;

    public CommitService(CommitDAORepository commitDAORepository, CreateCommitBean createCommitBean) {
        this.commitDAORepository = commitDAORepository;
        this.createCommitBean = createCommitBean;
    }

    public int saveCommit(UUID plannerId, UUID userId, List<CommitListDTO> commitListDTOList) {
        return createCommitBean.exec(plannerId, userId, commitListDTOList);
    }
}
