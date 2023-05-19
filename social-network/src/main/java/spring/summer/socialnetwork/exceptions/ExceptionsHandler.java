package spring.summer.socialnetwork.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException exception){
        System.out.println(exception.getMessage());

        return "Invalida data";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRunTimeException(RuntimeException exception){
        System.out.println(exception.getMessage());
        return "Bad Request";
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handle_email_exists_in_database(EmailExistsException e){
        System.out.println(e.getMessage());
        return "email already exists";
    }


}
