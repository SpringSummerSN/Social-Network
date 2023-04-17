package spring.summer.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;
import spring.summer.socialnetwork.models.User;

@Data
@Builder
public class MessageDto {
    private User sender;
    private String message;
}
