package gitpumta.gitpumta.user.controller;

import gitpumta.gitpumta.user.domain.dto.RankingResponseDTO;
import gitpumta.gitpumta.user.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    /**
     * 유저 종목 별 랭킹 조회
     */
    @GetMapping("/user")
    public RankingResponseDTO getRankingList() {

        return rankingService.getRankingList();

    }
}