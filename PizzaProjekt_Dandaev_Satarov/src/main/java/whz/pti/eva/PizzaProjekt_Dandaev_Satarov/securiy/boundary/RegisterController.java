package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.form.CustomerCreateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.validator.CustomerCreateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final CustomerService customerService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerCreateFormValidator customerCreateFormValidator;

    @Autowired
    public RegisterController(CustomerService customerService, UserService userService, PasswordEncoder passwordEncoder, CustomerCreateFormValidator customerCreateFormValidator) {
        this.customerService = customerService;
        this.userService = userService;
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
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
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

        return "redirect:/login";
    }
}
