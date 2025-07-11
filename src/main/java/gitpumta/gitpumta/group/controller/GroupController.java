package gitpumta.gitpumta.group.controller;

import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.domain.dto.GroupResponseDTO;
import gitpumta.gitpumta.group.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/group")
@CrossOrigin
public class GroupController {
    private final GroupService groupService;
    private final RestClient.Builder builder;

    public GroupController(GroupService groupService, RestClient.Builder builder) {
        this.groupService = groupService;
        this.builder = builder;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Map<String, Object>> createGroup(@RequestBody CreateGroupRequestDTO createGroupRequestDTO) {
        UUID id = groupService.createGroup(createGroupRequestDTO);
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("id", id);
        requestMap.put("message", id == null? "그룹 생성 성공" : "그룹 생성 실패");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
