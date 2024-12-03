package springApp.SpringSecApp.exception_handling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springApp.SpringSecApp.custom_exceptions.InvalidUserDataException;
import springApp.SpringSecApp.custom_exceptions.UserNotFoundException;
import springApp.SpringSecApp.dto.InvalidUserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpStatus> handleException(UserNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public ResponseEntity<InvalidUserDTO> handleException(InvalidUserDataException exception) throws JsonProcessingException {
        InvalidUserDTO invalidUserDTO = new InvalidUserDTO();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();

            switch (fieldName) {
                case "username":
                    invalidUserDTO.setUsernameError(errorMessage);
                    break;
                case "email":
                    invalidUserDTO.setEmailError(errorMessage);
                    break;
                case "age":
                    invalidUserDTO.setAgeError(errorMessage);
                    break;
                case "sex":
                    invalidUserDTO.setSexError(errorMessage);
                    break;
                case "phoneNumber":
                    invalidUserDTO.setPhoneNumberError(errorMessage);
                    break;
                case "roles":
                    invalidUserDTO.setRolesError(errorMessage);
                    break;
                case "password":
                    invalidUserDTO.setPasswordError(errorMessage);
                    break;
                default:
                    System.out.println("Unknown field: " + fieldName);
                    break;
            }
        }
        return new ResponseEntity<>(invalidUserDTO, HttpStatus.BAD_REQUEST);
    }
}
