package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.GroupDTO;
import spring.summer.socialnetwork.models.Group;
import spring.summer.socialnetwork.repositories.GroupRepository;

import java.util.List;


@Service
public class GroupService {
    public static final String GROUP_NOT_FOUND = "Group with id %s was not found.";
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(GROUP_NOT_FOUND, id)));
    }

    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }

    public void addGroup(GroupDTO groupDTO) {
        Group group = mapToGroup(groupDTO);
        groupRepository.save(group);
    }

    public void updateGroup(Long id, GroupDTO groupDTO) {
        groupRepository.findById(id).map(group -> {
            group.setName(groupDTO.getName());
            group.setDescription(groupDTO.getDescription());
            return groupRepository.save(group);
        }).orElseThrow(() -> new RuntimeException(String.format(GROUP_NOT_FOUND, id)));
    }

    private static Group mapToGroup(GroupDTO groupDTO) {
        return Group.builder()
                .name(groupDTO.getName())
                .description(groupDTO.getDescription())
                .build();
    }
}
