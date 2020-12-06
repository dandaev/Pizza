package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.form.CustomerCreateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.validator.CustomerCreateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.boundary.HomeController;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.CartIsNotLoggedIn;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class RegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    private final CustomerService customerService;
    private final UserService userService;
    private final CartService cartService;
    private final CartIsNotLoggedIn cartIsNotLoggedIn;
    private final PasswordEncoder passwordEncoder;
    private final CustomerCreateFormValidator customerCreateFormValidator;

    @Autowired
    public RegisterController(CustomerService customerService, UserService userService, CartService cartService, CartIsNotLoggedIn cartIsNotLoggedIn, PasswordEncoder passwordEncoder, CustomerCreateFormValidator customerCreateFormValidator) {
        this.customerService = customerService;
        this.userService = userService;
        this.cartService = cartService;
        this.cartIsNotLoggedIn = cartIsNotLoggedIn;
        this.passwordEncoder = passwordEncoder;
        this.customerCreateFormValidator = customerCreateFormValidator;
    }

    @InitBinder("customerCreateForm")
    public void initCustomerCreateFormBinder(WebDataBinder binder) {
        binder.addValidators(customerCreateFormValidator);
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        //
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("customerCreateForm") CustomerCreateForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("errors",bindingResult.getAllErrors().toString());
            return "redirect:/register";
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(form.getFirstName());
        customerDto.setLastName(form.getLastName());
        customerDto.setLoginName(form.getLoginName());
        customerDto.setPasswordHash(passwordEncoder.encode(form.getPasswordNew()));
        customerDto = customerService.create(customerDto);

        UserDto userDto = new UserDto();
        userDto.setCustomerDto(customerDto);
        userDto.setRole(Role.USER);
        userService.create(userDto);

        Cart cart = new Cart();
        cart.setItems(new HashMap<>());
        cart.setQuantity(0);
        cart.setUser(customerService.getCustomerNotDtoById(customerDto.getId()));
        cartService.create(cart);
        if (!cartIsNotLoggedIn.getItems().isEmpty()){
            for (Item item:cartIsNotLoggedIn.getItems()){
                cartService.addItemToCart(customerDto.getId(),item);
            }
        }
        return "redirect:/login";
    }
}
