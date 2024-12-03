package springApp.SpringSecApp.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 5, max = 20, message = "Имя пользователя должно быть от 5 до 20 символов")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 5, message = "Пароль должен содержать минимум 5 символов")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Неверный email")
    @Column(name = "email", unique = true)
    private String email;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Неверный номер телефона")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @NotNull(message = "Age cannot be null")
    @Min(value = 7, message = "Минимальный возраст: 7 лет")
    @Column(name = "age")
    private int age;

    @NotNull(message = "Sex cannot be null")
    @Column(name = "sex")
    private String sex;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password,
                String email, String phone, int age, String sex) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phone;
        this.age = age;
        this.sex = sex;
    }

    public void addRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
            role.addUser(this);
        }
    }

    public boolean hasRole(String role) {
        for (Role r : roles) {
            if (r.getRoleName().equals(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", phoneNumber=" + phoneNumber + ", age=" + age + ", sex=" + sex + "]";
    }
}
