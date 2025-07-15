package gitpumta.gitpumta.group.controller;

import gitpumta.gitpumta.group.domain.dto.CreateGroupRequestDTO;
import gitpumta.gitpumta.group.domain.dto.GroupListDTO;
import gitpumta.gitpumta.group.domain.dto.GroupResponseDTO;
import gitpumta.gitpumta.group.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;

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

    // 그룹 생성
    @PostMapping(value = "/create")
    public ResponseEntity<Map<String, Object>> createGroup(@RequestBody CreateGroupRequestDTO createGroupRequestDTO) {
        UUID id = groupService.createGroup(createGroupRequestDTO);
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("id", id);
        requestMap.put("message", id != null? "그룹 생성 성공" : "그룹 생성 실패");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 그룹 목록 조회
    @GetMapping(value = "/list")
    public ResponseEntity<List<GroupListDTO>> getGroupList() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    // 그룹 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchGroups(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(groupService.searchGroups(keyword));
    }
}
