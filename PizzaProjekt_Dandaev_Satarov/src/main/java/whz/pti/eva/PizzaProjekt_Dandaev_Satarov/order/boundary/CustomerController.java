package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.CustomerCreateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.CustomerUpdateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator.CustomerCreateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator.CustomerUpdateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;
    private final DeliveryAddressService deliveryAddressService;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    private final CustomerCreateFormValidator customerCreateFormValidator;
    private final CustomerUpdateFormValidator customerUpdateFormValidator;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService, DeliveryAddressService deliveryAddressService, CartService cartService, PasswordEncoder passwordEncoder, CustomerCreateFormValidator customerCreateFormValidator, CustomerUpdateFormValidator customerUpdateFormValidator) {
        this.customerService = customerService;
        this.userService = userService;
        this.deliveryAddressService = deliveryAddressService;
        this.cartService = cartService;
        this.passwordEncoder = passwordEncoder;
        this.customerCreateFormValidator = customerCreateFormValidator;
        this.customerUpdateFormValidator = customerUpdateFormValidator;
    }

    @InitBinder("customerCreateForm")
    public void initCustomerCreateFormBinder(WebDataBinder binder) {
        binder.addValidators(customerCreateFormValidator);
    }

    @InitBinder("customerUpdateForm")
    public void initCustomerUpdateFormBinder(WebDataBinder binder) {
        binder.addValidators(customerUpdateFormValidator);
    }

    @GetMapping("/all")
    public String getCustomersPage(
            Model model
    ) {
        List<CustomerDto> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer_all";
    }

    @PostMapping("/new")
    public String addNewCustomer(
            HttpServletRequest request,
            @Valid @ModelAttribute("customerCreateForm") CustomerCreateForm form,
            BindingResult bindingResult
    ) {
        String referer = request.getHeader("Referer");
        if (bindingResult.hasErrors()) {
            return "redirect:" + referer;
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
        cart.setUser(customerService.getCustomerNotDtoById(customerDto.getId()));
        cart.setQuantity(0);
        cart.setItems(new HashMap<>());
        cartService.create(cart);

        return "redirect:" + referer;
    }

    @GetMapping("/{id}/info")
    public String getCustomerInfoPage(
            @PathVariable String id,
            Model model
    ) {
        CustomerDto customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("addresses", deliveryAddressService.getDeliveryAddressesByCustomer(customer));
        return "customer_info";
    }

    @PostMapping("/{id}/update")
    public String updateCustomerInfo(
            HttpServletRequest request,
            @PathVariable String id,
            @Valid @ModelAttribute("customerUpdateForm") CustomerUpdateForm form,
            BindingResult bindingResult
    ) {
        String referer = request.getHeader("Referer");
        if (bindingResult.hasErrors()) {
            return "redirect:" + referer;
        }
        CustomerDto customer = customerService.getCustomerById(id);
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setLoginName(form.getLoginName());
        customer.setPasswordHash(passwordEncoder.encode(form.getPasswordNew()));
        customer = customerService.update(customer);

        UserDto userDto = userService.getUserByCustomerId(customer.getId());
        userDto.setCustomerDto(customer);
        userService.update(userDto);

        return "redirect:" + referer;
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        if (customerService.existsById(id)) {
            CustomerDto customer = customerService.getCustomerById(id);
            customerService.delete(customer);
            return "redirect:/customer/all";
        }
        return "redirect:/customer/{id}/info";
    }
}
