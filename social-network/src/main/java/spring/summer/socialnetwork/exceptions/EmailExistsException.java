package spring.summer.socialnetwork.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class EmailExistsException extends Exception{

    public EmailExistsException(String message) {
        super(message);
    }
}
