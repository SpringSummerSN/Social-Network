package spring.summer.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String email;
    private String name;
    private String surname;
    private String password;
}
