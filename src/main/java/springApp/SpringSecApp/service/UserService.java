package springApp.SpringSecApp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import springApp.SpringSecApp.dto.UserDTO;
import springApp.SpringSecApp.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(UserDTO userDTO);

    void delete(int id);

    void update(int id, UserDTO userDTO);

    User findByUsername(String username);

    List<User> findAllUsersWithRoles();

    User findUserByIdWithRoles(int id);

    UserDetails loadUserByUsername(String username);
}
