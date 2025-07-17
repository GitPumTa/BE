package gitpumta.gitpumta.commit.bean.small;

import gitpumta.gitpumta.commit.repository.CommitDAORepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class CountCommitSmallBean {
    private final CommitDAORepository commitDAORepository;

    public CountCommitSmallBean(CommitDAORepository commitDAORepository) {
        this.commitDAORepository = commitDAORepository;
    }

    public int Todayexec(UUID plannerId, LocalDate today){
        int todayCount = commitDAORepository.countByPlannerIdAndTimeBetween(
                plannerId,
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );

        return todayCount;
    }
}
