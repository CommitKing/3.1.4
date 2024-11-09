package springApp.SpringSecApp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import springApp.SpringSecApp.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    /*void save(User user);

    void delete(User user);

    User findByUsername(String username);

    List<User> findAllUsersWithRoles();

    User findUserByIdWithRoles(int id);

    UserDetails loadUserByUsername(String username);
*/
    UserDetails loadUserByUsername(String username);

    User getUserByUsername(String username);

    List<User> getAllUsersWithRole();

    void saveUser(User user);

    void deleteUser(int id);

    User getUserById(int id);


}
