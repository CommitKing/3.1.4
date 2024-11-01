package springApp.SpringSecApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springApp.SpringSecApp.model.User;
import springApp.SpringSecApp.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user, User updatedUser) {
        updatedUser.setId(user.getId());
        userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsersWithRoles() {
        return userRepository.findAllUsersWithRoles().orElse(null);
    }

    @Override
    public User findUserByIdWithRole(int id) {
        return userRepository.findUserByIdWithRoles(id).orElse(null);
    }
}
