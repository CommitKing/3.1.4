package springApp.SpringSecApp.service;

import org.springframework.transaction.annotation.Transactional;
import springApp.SpringSecApp.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void update(User user, User updatedUser);

    void delete(User user);

    User findByUsername(String username);

    List<User> findAllUsersWithRoles();

    User findUserByIdWithRole(int id);
}
