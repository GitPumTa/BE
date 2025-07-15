package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetMemberTimersResponseDTO {
    private String message;

    private String myMonitoringGroup;
    private String myMonitoringGroupDescription;
    private int myRank;
    private String myName;

    private List<DurationLeaderDTO> durationLeaders;
    private List<CommitLeaderDTO> commitLeaders;

    @Data
    public static class DurationLeaderDTO {
        private String name;
        private int duration;
        private int rank;
        private int status;
        private LocalDateTime sendAt; // update_at과 같음
    }

    @Data
    public static class CommitLeaderDTO {
        private String name;
        private int commitCount;
        private int rank;
    }
}