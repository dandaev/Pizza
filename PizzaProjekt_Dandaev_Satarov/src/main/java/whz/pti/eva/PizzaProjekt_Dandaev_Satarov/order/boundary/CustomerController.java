package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.form.CustomerCreateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.form.CustomerUpdateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.validator.CustomerCreateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.validator.CustomerUpdateFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final DeliveryAddressService deliveryAddressService;

    private final CustomerCreateFormValidator customerCreateFormValidator;
    private final CustomerUpdateFormValidator customerUpdateFormValidator;

    @Autowired
    public CustomerController(CustomerService customerService, DeliveryAddressService deliveryAddressService, CustomerCreateFormValidator customerCreateFormValidator, CustomerUpdateFormValidator customerUpdateFormValidator) {
        this.customerService = customerService;
        this.deliveryAddressService = deliveryAddressService;
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
        customerDto.setPasswordHash(form.getPasswordNew());
        customerService.create(customerDto);
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
        customer.setPasswordHash(form.getPasswordNew());
        customerService.update(customer);
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
