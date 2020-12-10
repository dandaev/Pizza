package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.User;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    User getUserById(String id);
    Optional<User> getUserByLoginName(String loginName);
    UserDto getUserByCustomerId(String id);
    boolean existsByLoginName(String loginName);
    Collection<UserDto> getAllUsers();
    User create(UserDto userDto);
    boolean update(UserDto userDto);
    boolean existsById(String id);
}
