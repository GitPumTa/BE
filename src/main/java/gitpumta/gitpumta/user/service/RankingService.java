package gitpumta.gitpumta.user.service;

import gitpumta.gitpumta.commit.domain.CommitDAO;
import gitpumta.gitpumta.user.domain.UserDAO;
import gitpumta.gitpumta.user.domain.dto.RankingResponseDTO;
import gitpumta.gitpumta.user.domain.dto.UserRatingInfoDTO;
import gitpumta.gitpumta.user.repository.UserDAORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class RankingService {

    private final UserDAORepository userDAORepository;

    public RankingResponseDTO getRankingList() {
        List<UserDAO> userList = userDAORepository.findAll();

        List<UserRatingInfoDTO> timeRankingList = createRankingList(userList, "duration");
        List<UserRatingInfoDTO> commitRankingList = createRankingList(userList, "commit");

        return RankingResponseDTO.builder()
                .timeRanking(timeRankingList)
                .commitRanking(commitRankingList)
                .build();
    }


    private List<UserRatingInfoDTO> createRankingList(List<UserDAO> users, String rankingType) {
        Comparator<UserDAO> comparator;
        Function<UserDAO, Integer> ratingGetter;

        boolean isTimeRanking = rankingType.equals("duration");

        switch (rankingType) {
            case "duration" -> {
                comparator = Comparator.comparing(
                        UserDAO::getTotalDuration,
                        Comparator.nullsLast(Integer::compareTo)
                ).reversed();
                ratingGetter = UserDAO::getTotalDuration;
            }
            case "commit" -> {
                comparator = Comparator.comparing(
                        UserDAO::getTotalCommit,
                        Comparator.nullsLast(Integer::compareTo)
                ).reversed();
                ratingGetter = UserDAO::getTotalCommit;
            }
            default -> throw new IllegalArgumentException("Unsupported ranking type: " + rankingType);
        }

        List<UserDAO> sortedUsers = users.stream()
                .sorted(comparator)
                .toList();

        List<UserRatingInfoDTO> rankingList = new ArrayList<>();
        int rank = 1;
        int count = 1;
        Integer previousRating = null;

        for (UserDAO user : sortedUsers) {
            Integer currentRating = ratingGetter.apply(user);
            if (currentRating == null) continue;

            if (previousRating != null && !currentRating.equals(previousRating)) {
                rank = count;
            }

            rankingList.add(UserRatingInfoDTO.builder()
                    .rank(rank)
                    .nickname(user.getNickname())
                    .timeDuration(isTimeRanking ? currentRating : null)
                    .commitDuration(isTimeRanking ? null : currentRating)
                    .build());

            previousRating = currentRating;
            count++;
        }

        return rankingList.stream().limit(3).toList();
    }

}