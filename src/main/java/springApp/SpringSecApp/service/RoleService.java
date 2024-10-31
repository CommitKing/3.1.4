package springApp.SpringSecApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.SpringSecApp.model.Role;
import springApp.SpringSecApp.repository.RoleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return Optional.of(roleRepository.findAll())
                .orElseGet(Collections::emptyList);

    }
}
