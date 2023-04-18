package spring.summer.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupDTO {
    private String name;
    private String description;
}
