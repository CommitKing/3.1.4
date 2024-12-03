package springApp.SpringSecApp.custom_exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@NoArgsConstructor
@Setter
@Getter
public class InvalidUserDataException extends RuntimeException {
    private BindingResult bindingResult;

    public InvalidUserDataException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
