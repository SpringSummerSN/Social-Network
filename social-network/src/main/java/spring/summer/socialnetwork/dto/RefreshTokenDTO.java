package spring.summer.socialnetwork.dto;

import org.apache.kafka.common.protocol.types.Field;

public class RefreshTokenDTO {
    private String message;
    private String token;

    public RefreshTokenDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
