package spring.summer.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.summer.socialnetwork.models.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String title;
    private String description;
    private User creator;
}
