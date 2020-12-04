package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.implementation;

import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.User;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.repository.UserRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomerService customerService;

    public UserServiceImpl(UserRepository userRepository, CustomerService customerService) {
        this.userRepository = userRepository;
        this.customerService = customerService;
    }

    @Override
    public UserDto getUserById(String id) {
        return null;
    }

    @Override
    public Optional<User> getUserByLoginName(String loginName) {
        return userRepository.getUsersByLoginName(loginName);
    }

    @Override
    public UserDto getUserByCustomerId(String id) {
        User user = userRepository.getUsersByCustomerId(id);
        if (user != null){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setRole(user.getRole());
            userDto.setCustomerDto(customerService.toDto(user.getCustomer()));
            userDto.setLoginName(user.getLoginName());
            userDto.setPasswordHash(user.getPasswordHash());
            return userDto;
        }
        return null;
    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return userRepository.existsByLoginName(loginName);
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public User create(UserDto userDto) {
        User user = new User();
        user.setLoginName(userDto.getLoginName());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setRole(userDto.getRole());
        user.setCustomer(customerService.fromDto(customerService.getCustomerById(userDto.getCustomerDto().getId())));
        return userRepository.save(user);
    }

    @Override
    public boolean update(UserDto userDto) {
        if(existsById(userDto.getId())){
            User user = new User();
            user.setCustomer(customerService.fromDto(userDto.getCustomerDto()));
            user.setId(userDto.getId());
            user.setLoginName(userDto.getLoginName());
            user.setPasswordHash(userDto.getPasswordHash());
            user.setRole(userDto.getRole());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }
}
