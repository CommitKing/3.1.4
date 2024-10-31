package springApp.SpringSecApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springApp.SpringSecApp.model.User;
import springApp.SpringSecApp.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(User user, User updatedUser) {
        updatedUser.setId(user.getId());
        userRepository.save(updatedUser);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> findAllUsersWithRoles() {
        return userRepository.findAllUsersWithRoles().orElse(null);
    }

    public User findUserByIdWithRole(int id) {
        return userRepository.findUserByIdWithRoles(id).orElse(null);
    }
}
