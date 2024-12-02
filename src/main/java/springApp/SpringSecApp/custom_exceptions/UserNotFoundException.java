package springApp.SpringSecApp.custom_exceptions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;

@NoArgsConstructor
@Setter
@Getter
public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(String message) {
        super(message);
    }
}
