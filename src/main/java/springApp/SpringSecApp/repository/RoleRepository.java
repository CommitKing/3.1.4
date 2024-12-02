package springApp.SpringSecApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springApp.SpringSecApp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.roleName=:name")
    Role findByName(String name);
}
