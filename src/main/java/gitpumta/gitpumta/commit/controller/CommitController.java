package gitpumta.gitpumta.commit.controller;

import gitpumta.gitpumta.commit.bean.CreateCommitBean;
import gitpumta.gitpumta.commit.domain.dto.CommitListDTO;
import gitpumta.gitpumta.commit.domain.dto.CreateCommitRequestDTO;
import gitpumta.gitpumta.commit.service.CommitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/commit")
@CrossOrigin
public class CommitController {
    private final CommitService commitService;
    private final CreateCommitBean createCommitBean;
    public CommitController(CommitService commitService, CreateCommitBean createCommitBean) {
        this.commitService = commitService;
        this.createCommitBean = createCommitBean;
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Map<String, Object>> updateCommit(@RequestBody CreateCommitRequestDTO createCommitRequestDTO) {
        int count = createCommitBean.exec(
                createCommitRequestDTO.getPlannerId(),
                createCommitRequestDTO.getUserId(),
                createCommitRequestDTO.getCommits()
        );

        Map<String,Object> requestMap = new HashMap<>();
        if (count == -1){
            requestMap.put("is_success", false);
            requestMap.put("message", "입력받은 커밋 테이더가 없습니다.");
        }else{
            requestMap.put("is_success", true);
            requestMap.put("commit_count", count);
        }
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
