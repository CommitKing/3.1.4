package springApp.SpringSecApp.start_app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springApp.SpringSecApp.model.Role;
import springApp.SpringSecApp.model.User;

@Component
@Transactional
public class AppStart implements ApplicationRunner {
    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppStart(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        entityManager.persist(new Role("USER"));
        entityManager.persist(new Role("ADMIN"));
        entityManager.flush();  // Принудительно записываем данные в базу

        Role userRole = entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :name", Role.class)
                .setParameter("name", "USER")
                .getSingleResult();
        Role adminRole = entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :name", Role.class)
                .setParameter("name", "ADMIN")
                .getSingleResult();
        User user = new User();
        user.setUsername("eugene");
        user.setEmail("salnikovzena@gmail.com");
        user.setPassword(passwordEncoder.encode("111111"));
        user.setSex("male");
        user.setAge(20);
        user.setPhoneNumber("89206206466");
        user.addRole(userRole);
        entityManager.persist(user);

        User admin = new User();
        admin.setUsername("kristina");
        admin.setEmail("salnikovakristina@gmail.com");
        admin.setPassword(passwordEncoder.encode("111111"));
        admin.setSex("female");
        admin.setAge(19);
        admin.setPhoneNumber("89004828680");
        admin.addRole(adminRole);
        admin.addRole(userRole);
        entityManager.persist(admin);
    }
}