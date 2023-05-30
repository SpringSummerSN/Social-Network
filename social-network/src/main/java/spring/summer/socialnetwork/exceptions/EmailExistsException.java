package spring.summer.socialnetwork.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
public class EmailExistsException extends Exception{

    public EmailExistsException() {
        super("Email already exists");
    }
}
