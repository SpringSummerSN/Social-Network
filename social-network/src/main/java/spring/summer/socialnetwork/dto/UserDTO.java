package spring.summer.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;
import spring.summer.socialnetwork.validator.ValidPassword;

@Data
@Builder

public class UserDTO {
    private String email;
    private String name;
    private String surname;
    @ValidPassword
    private String password;
}
