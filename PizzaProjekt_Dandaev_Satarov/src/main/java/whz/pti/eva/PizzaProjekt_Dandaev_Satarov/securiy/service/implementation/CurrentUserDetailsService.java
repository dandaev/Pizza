package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.CurrentUser;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.User;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.CartIsNotLoggedIn;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final CartIsNotLoggedIn cartIsNotLoggedIn;
    private final CartService cartService;

    @Autowired
    public CurrentUserDetailsService(UserService userService, CartIsNotLoggedIn cartIsNotLoggedIn, CartService cartService) {
        this.userService = userService;
        this.cartIsNotLoggedIn = cartIsNotLoggedIn;
        this.cartService = cartService;
    }

    @Override
    public CurrentUser loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user =
                userService.getUserByLoginName(loginName)
                .orElseThrow(()->
                        new UsernameNotFoundException("User mit login = " + loginName +
                                " ist nicht existiert"));
        if (!cartIsNotLoggedIn.getItems().isEmpty()){
            for (Item item:cartIsNotLoggedIn.getItems()){
                cartService.addItemToCart(user.getCustomer().getId(),item);
            }
        }
        return new CurrentUser(user);
    }
}
