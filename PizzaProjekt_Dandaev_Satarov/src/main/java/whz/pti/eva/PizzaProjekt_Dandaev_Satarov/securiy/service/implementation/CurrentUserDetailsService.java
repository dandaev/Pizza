package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.CurrentUser;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.User;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String loginName) throws UsernameNotFoundException {
        System.out.println(loginName);
        User user =
                userService.getUserByLoginName(loginName)
                .orElseThrow(()->
                        new UsernameNotFoundException("User mit login = " + loginName +
                                " ist nicht existiert"));
        System.out.println(user.getLoginName()+" "+ user.getPasswordHash());
        return new CurrentUser(user);
    }
}
