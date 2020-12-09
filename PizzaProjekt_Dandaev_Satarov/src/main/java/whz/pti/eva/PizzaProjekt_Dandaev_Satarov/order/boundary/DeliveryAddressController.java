package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.DeliveryAddressCreateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.DeliveryAddressUpdateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator.DeliveryAddressCreateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator.DeliveryAddressUpdateFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/deliveryaddress")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;
    private final CustomerService customerService;

    private final DeliveryAddressCreateFormValidator deliveryAddressCreateFormValidator;
    private final DeliveryAddressUpdateFormValidator deliveryAddressUpdateFormValidator;

    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService,
                                     CustomerService customerService, DeliveryAddressCreateFormValidator deliveryAddressCreateFormValidator,
                                     DeliveryAddressUpdateFormValidator deliveryAddressUpdateFormValidator) {
        this.deliveryAddressService = deliveryAddressService;
        this.customerService = customerService;
        this.deliveryAddressCreateFormValidator = deliveryAddressCreateFormValidator;
        this.deliveryAddressUpdateFormValidator = deliveryAddressUpdateFormValidator;
    }

    @InitBinder("deliveryAddressCreateForm")
    public void initDeliveryAddressCreateFormBinder(WebDataBinder binder) {
        binder.addValidators(deliveryAddressCreateFormValidator);
    }

    @InitBinder("deliveryAddressUpdateForm")
    public void initDeliveryAddressUpdateForm(WebDataBinder binder) {
        binder.addValidators(deliveryAddressUpdateFormValidator);
    }

    @PostMapping("/create")
    public String createDeliveryAddress(
            HttpServletRequest request,
            @Valid @ModelAttribute("deliveryAddressCreateForm") DeliveryAddressCreateForm form,
            BindingResult bindingResult
    ) {
        String referer = request.getHeader("Referer");
        if (bindingResult.hasErrors()) {
            return "redirect:" + referer;
        }
        CustomerDto customerDto = customerService.getCustomerById(form.getCustomerId());

        DeliveryAddressDto deliveryAddressDto = new DeliveryAddressDto();
        deliveryAddressDto.setStreet(form.getStreet());
        deliveryAddressDto.setHouseNumber(form.getHouseNumber());
        deliveryAddressDto.setTown(form.getTown());
        deliveryAddressDto.setPostalCode(form.getPostalCode());
        deliveryAddressDto.setCustomer(customerDto);
        deliveryAddressService.create(deliveryAddressDto);

        return "redirect:" + referer;
    }

    @PostMapping("/update")
    public String updateDeliveryAddress(
            HttpServletRequest request,
            @Valid @ModelAttribute("deliveryAddressUpdateForm") DeliveryAddressUpdateForm form,
            BindingResult bindingResult
    ) {
        String referer = request.getHeader("Referer");
        if (bindingResult.hasErrors()) {
            return "redirect:" + referer;
        }
        DeliveryAddressDto deliveryAddressDto = deliveryAddressService.getDeliveryAddressById(form.getId());
        deliveryAddressDto.setStreet(form.getStreet());
        deliveryAddressDto.setHouseNumber(form.getHouseNumber());
        deliveryAddressDto.setTown(form.getTown());
        deliveryAddressDto.setPostalCode(form.getPostalCode());
        deliveryAddressService.update(deliveryAddressDto);

        return "redirect:" + referer;
    }

    @PostMapping("/delete/{id}")
    public String deleteDeliveryAddress(
            HttpServletRequest request,
            @PathVariable String id
    ) {
        String referer = request.getHeader("Referer");
        if (deliveryAddressService.existsById(id)) {
            DeliveryAddressDto deliveryAddressDto = deliveryAddressService.getDeliveryAddressById(id);
            deliveryAddressService.delete(deliveryAddressDto);
            return "redirect:" + referer;
        }
        return "redirect:" + referer;
    }
}
