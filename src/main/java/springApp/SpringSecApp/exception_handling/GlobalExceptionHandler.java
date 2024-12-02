package springApp.SpringSecApp.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springApp.SpringSecApp.custom_exceptions.InvalidUserDataException;
import springApp.SpringSecApp.custom_exceptions.UserNotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<InvalidUserDataException> handleException(UserNotFoundException e) {
        InvalidUserDataException data = new InvalidUserDataException();
        data.setMessage(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public ResponseEntity<InvalidUserDataException> handleException(Exception e) {
        InvalidUserDataException data = new InvalidUserDataException();
        data.setMessage(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
