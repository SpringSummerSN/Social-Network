package spring.summer.socialnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.summer.socialnetwork.dto.GroupDTO;
import spring.summer.socialnetwork.models.Group;
import spring.summer.socialnetwork.services.GroupService;
import java.util.List;

@RestController
@RequestMapping("api/v1/group")
public class GroupController
{
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAllGroups()
    {
        return groupService.getAllGroup();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupById(@PathVariable Long id) {
        groupService.deleteGroupById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addGroup(@RequestBody GroupDTO groupDTO) {
        groupService.addGroup(groupDTO);
    }

    @PutMapping("/{id}")
    public void updateGroup(@PathVariable Long id, @RequestBody GroupDTO groupDTO) {
        groupService.updateGroup(id, groupDTO);
    }
}
