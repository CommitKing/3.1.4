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
public class UserDTO implements Serializable {
    @NotNull(message = "Username cannot be null")
    @Size(min = 5, max = 20, message = "username length must be between 5 and 20")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 5, message = "password length must be 5 and more")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email(message = "This email is not right")
    private String email;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Age cannot be null")
    @Min(value = 7, message = "Age must be at least 7")
    private int age;

    @NotNull(message = "Sex cannot be null")
    private String sex;

    private Set<String> roles = new HashSet<>();
}
