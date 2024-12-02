package springApp.SpringSecApp.custom_exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class InvalidUserDataException extends RuntimeException {
    private String message;

    public InvalidUserDataException(String message) {
        super(message);
    }
}
