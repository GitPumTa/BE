package gitpumta.gitpumta.commit.controller;

import gitpumta.gitpumta.commit.service.CommitService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/commit")
@CrossOrigin
public class CommitController {
    private final CommitService commitService;

    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }
}
