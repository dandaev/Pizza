package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByLoginName(String loginName);
    boolean existsById(String id);
    Optional<User> getUsersByLoginName(String loginName);
    User getUsersByCustomerId(String id);
    User getUsersById(String id);
}
