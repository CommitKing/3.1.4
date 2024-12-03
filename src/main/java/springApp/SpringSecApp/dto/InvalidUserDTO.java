package springApp.SpringSecApp.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class InvalidUserDTO implements Serializable {
    private String usernameError = "";
    private String passwordError = "";
    private String emailError = "";
    private String phoneNumberError = "";
    private String ageError = "";
    private String sexError = "";
    private String rolesError = "";
}
