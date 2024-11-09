package springApp.SpringSecApp.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springApp.SpringSecApp.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*public Optional<User> findByUsername(String username);

    @Query("select u from User u left join fetch u.roles")
    List<User> findAllUsersWithRoles();

    @Query("select u from User u left join fetch u.roles where u.id = :id")
    public Optional<User> findUserByIdWithRoles(int id);*/

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findUserByUsername(String username);

    @Query("select u from User u left join fetch u.roles")
    List<User> findAllUsersWithRole();

    @Query("select u from User u left join fetch u.roles where u.id = :id")
    Optional<User> findUserByIdWithRole(int id);
}
