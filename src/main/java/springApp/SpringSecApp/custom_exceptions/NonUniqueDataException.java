package springApp.SpringSecApp.custom_exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonUniqueDataException extends RuntimeException {
    public NonUniqueDataException(String message) {
        super(message);
    }
}
