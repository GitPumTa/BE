package gitpumta.gitpumta.commit.bean;

import gitpumta.gitpumta.commit.bean.small.CountCommitSmallBean;
import gitpumta.gitpumta.commit.domain.CommitDAO;
import gitpumta.gitpumta.commit.domain.dto.CommitListDTO;
import gitpumta.gitpumta.commit.repository.CommitDAORepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CreateCommitBean {
    private final CommitDAORepository commitDAORepository;
    private final CountCommitSmallBean countCommitSmallBean;

    public CreateCommitBean(CommitDAORepository commitDAORepository, CountCommitSmallBean countCommitSmallBean) {
        this.commitDAORepository = commitDAORepository;
        this.countCommitSmallBean = countCommitSmallBean;
    }

    public int exec(UUID plannerId, UUID userId, List<CommitListDTO> commitListDTOList){
        if (commitListDTOList == null || commitListDTOList.isEmpty()) {
            return -1;
        }

        // 해당 plannerId, userId의 최신 커밋 시간 조회
        CommitDAO latestCommit = commitDAORepository.findTop1ByPlannerIdOrderByTimeDesc(plannerId);
        LocalDateTime lastCommitTime = (latestCommit != null) ? latestCommit.getTime() : LocalDateTime.MIN;

        // lastCommitTime보다 최신인 커밋만 저장
        List<CommitDAO> commitsToSave = commitListDTOList.stream()
                .filter(dto -> dto.getTime().isAfter(lastCommitTime))
                .map(dto -> CommitDAO.builder()
                        .id(UUID.randomUUID())
                        .plannerId(plannerId)
                        .userId(userId)
                        .message(dto.getMessage())
                        .time(dto.getTime())
                        .build())
                .toList();
        if (!commitsToSave.isEmpty()) {
            commitDAORepository.saveAll(commitsToSave);
        }
        int count = countCommitSmallBean.Todayexec(plannerId, LocalDate.from(LocalDateTime.now()));
        return count;
    }
}

