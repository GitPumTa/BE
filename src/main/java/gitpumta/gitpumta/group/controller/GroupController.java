package gitpumta.gitpumta.group.controller;

import gitpumta.gitpumta.group.domain.dto.*;
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
    public ResponseEntity<Map<String, Object>> getGroupList() {
        List<GroupListDTO> groupList = groupService.getAllGroups();
        Map<String, Object> response = new HashMap<>();
        response.put("data", groupList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 그룹 검색
    @GetMapping(value = "/search")
    public ResponseEntity<Map<String, Object>> searchGroups(@RequestParam("keyword") String keyword) {
        List<GroupResponseDTO> matchGroups = groupService.searchGroups(keyword);
        Map<String, Object> response = new HashMap<>();
        response.put("data", matchGroups);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 득정 그룹 상세 정보 조회
    @GetMapping(value = "/detail")
    public ResponseEntity<Map<String, Object>> getGroupDetail(@RequestParam UUID groupId) {
        GroupResponseDTO groupResponseDTO = groupService.getGroupDetail(groupId);
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("group",groupResponseDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 특정 그룹 가입
    @PostMapping(value = "/join")
    public ResponseEntity<Map<String, Object>> joinGroup(@RequestBody JoinGroupRequestDTO joinGroupRequestDTO) {
        groupService.joinGroup(joinGroupRequestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "그룹 가입 성공");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateGroup(@RequestBody UpdateGroupRequestDTO dto) {
        groupService.updateGroup(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "그룹 정보 수정 완료");
        return ResponseEntity.ok(response);
    }
}
