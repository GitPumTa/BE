package gitpumta.gitpumta.timer.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RepoDTO {
    private UUID id;

    private String title;

    private String subtitle;

    private String repo_address;

    private int duration;
}
