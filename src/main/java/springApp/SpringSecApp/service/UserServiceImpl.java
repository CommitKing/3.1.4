package springApp.SpringSecApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springApp.SpringSecApp.UserDetails.UserDetailsImpl;
import springApp.SpringSecApp.custom_exceptions.InvalidUserDataException;
import springApp.SpringSecApp.custom_exceptions.NonUniqueDataException;
import springApp.SpringSecApp.custom_exceptions.UserNotFoundException;
import springApp.SpringSecApp.dto.UserDTO;
import springApp.SpringSecApp.model.Role;
import springApp.SpringSecApp.model.User;
import springApp.SpringSecApp.repository.RoleRepository;
import springApp.SpringSecApp.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService selfService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.selfService = this;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return new UserDetailsImpl(foundedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsersWithRoles() {
        return Optional.of(userRepository.findAllUsersWithRoles())
                .orElseGet(Collections::emptyList);
    }

    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new NonUniqueDataException("username");
        } else if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new NonUniqueDataException("email");
        } else if (userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())) {
            throw new NonUniqueDataException("phoneNumber");
        }

        User userToSave = new User();
        userToSave.setUsername(userDTO.getUsername());
        userToSave.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userToSave.setAge(userDTO.getAge());
        userToSave.setEmail(userDTO.getEmail());
        userToSave.setSex(userDTO.getSex());
        userToSave.setPhoneNumber(String.valueOf(userDTO.getPhoneNumber()));
        Set<Role> roles = userDTO.getRoles().stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());
        userToSave.setRoles(roles);

        userRepository.save(userToSave);
    }

    @Override
    @Transactional
    public void update(int id, UserDTO userDTO) {
        User userToUpdate = userRepository.findUserByIdWithRoles(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        if (userRepository.existsByUsername(userDTO.getUsername()) &&
                !userToUpdate.getUsername().equals(userDTO.getUsername())) {
            throw new NonUniqueDataException("username");
        } else if (userRepository.existsByEmail(userDTO.getEmail()) &&
                !userToUpdate.getEmail().equals(userDTO.getEmail())) {
            throw new NonUniqueDataException("email");
        } else if (userRepository.existsByPhoneNumber(userDTO.getPhoneNumber()) &&
                !userToUpdate.getPhoneNumber().equals(userDTO.getPhoneNumber())) {
            throw new NonUniqueDataException("phoneNumber");
        }

        userToUpdate.setUsername(userDTO.getUsername());
        userToUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setAge(userDTO.getAge());
        userToUpdate.setPhoneNumber(userDTO.getPhoneNumber());
        userToUpdate.setSex(userDTO.getSex());

        Set<Role> roles = userDTO.getRoles().stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());
        userToUpdate.setRoles(roles);

        userRepository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.delete(selfService.findUserByIdWithRoles(id));
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByIdWithRoles(int id) {
        return userRepository.findUserByIdWithRoles(id)
                .orElseThrow(() -> new UserNotFoundException(id + " not found"));
    }
}
