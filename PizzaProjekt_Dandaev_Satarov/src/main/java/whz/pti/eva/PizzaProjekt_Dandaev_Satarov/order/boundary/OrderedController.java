package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.DeliveryAddress;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Ordered;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.DeliveryAddressCreateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.DeliveryAddressUpdateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.OrderedItemService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.OrderedService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator.CustomerUpdateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator.DeliveryAddressCreateFormValidator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.CurrentUser;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class OrderedController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedController.class);

    private final CartService cartService;
    private final OrderedService orderedService;
    private final DeliveryAddressService deliveryAddressService;
    private final CustomerService customerService;
    private final OrderedItemService orderedItemService;

    private final CustomerUpdateFormValidator customerUpdateFormValidator;
    private final DeliveryAddressCreateFormValidator deliveryAddressCreateFormValidator;

    public OrderedController(CartService cartService, OrderedService orderedService, DeliveryAddressService deliveryAddressService, CustomerService customerService, OrderedItemService orderedItemService, CustomerUpdateFormValidator customerUpdateFormValidator, DeliveryAddressCreateFormValidator deliveryAddressCreateFormValidator) {
        this.cartService = cartService;
        this.orderedService = orderedService;
        this.deliveryAddressService = deliveryAddressService;
        this.customerService = customerService;
        this.orderedItemService = orderedItemService;
        this.customerUpdateFormValidator = customerUpdateFormValidator;
        this.deliveryAddressCreateFormValidator = deliveryAddressCreateFormValidator;
    }

    @InitBinder("customerUpdateForm")
    public void initCustomerUpdateFormBinder(WebDataBinder binder) {
        binder.addValidators(customerUpdateFormValidator);
    }
    @InitBinder("deliveryAddressCreateForm")
    public void initDeliveryAddressCreateFormBinder(WebDataBinder binder) {
        binder.addValidators(deliveryAddressCreateFormValidator);
    }

    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String getOrderPage(@RequestParam Optional<String> error, Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        Ordered ordered = orderedService.convertCartToOrdered(currentUser.getCustomerId());
        model.addAttribute("ordered",ordered);
        model.addAttribute("checkList",orderedService.getCheckOfItems(ordered));
        model.addAttribute("count",orderedService.getCommonCount(ordered));
        model.addAttribute("commonPrice",orderedService.getCommonPrice(ordered));
        model.addAttribute("addresses",deliveryAddressService.getDeliveryAddressesByCustomer(customerService.getCustomerById(currentUser.getCustomerId())));
        return "order";
    }

    @PostMapping("/deliveryaddresscreate")
    public String createDeliveryAddress(
            HttpServletRequest request,
            @Valid @ModelAttribute("deliveryAddressCreateForm") DeliveryAddressCreateForm form,
            BindingResult bindingResult
    ) {
        String referer = request.getHeader("Referer");
        if (bindingResult.hasErrors()) {
            return "redirect:order";
        }
        CustomerDto customerDto = customerService.getCustomerById(form.getCustomerId());

        DeliveryAddressDto deliveryAddressDto = new DeliveryAddressDto();
        deliveryAddressDto.setStreet(form.getStreet());
        deliveryAddressDto.setHouseNumber(form.getHouseNumber());
        deliveryAddressDto.setTown(form.getTown());
        deliveryAddressDto.setPostalCode(form.getPostalCode());
        deliveryAddressDto.setCustomer(customerDto);
        DeliveryAddress deliveryAddress = deliveryAddressService.create(deliveryAddressDto);
        Ordered ordered = orderedService.getOrderedByUserId(deliveryAddressDto.getCustomer().getId());
        ordered.setDeliveryAddress(deliveryAddress);
        orderedService.update(ordered);

        return "redirect:order";
    }

    @PostMapping("/deliveryaddressupdate")
    public String updateDeliveryAddress(
            HttpServletRequest request,
            @Valid @ModelAttribute("deliveryAddressUpdateForm") DeliveryAddressUpdateForm form,
            BindingResult bindingResult
    ) {
        String referer = request.getHeader("Referer");
        if (bindingResult.hasErrors()) {
            return "redirect:order";
        }
        DeliveryAddressDto deliveryAddressDto = deliveryAddressService.getDeliveryAddressById(form.getId());
        deliveryAddressDto.setStreet(form.getStreet());
        deliveryAddressDto.setHouseNumber(form.getHouseNumber());
        deliveryAddressDto.setTown(form.getTown());
        deliveryAddressDto.setPostalCode(form.getPostalCode());
        DeliveryAddress deliveryAddress = deliveryAddressService.update(deliveryAddressDto);
        Ordered ordered = orderedService.getOrderedByUserId(deliveryAddressDto.getCustomer().getId());
        ordered.setDeliveryAddress(deliveryAddress);
        orderedService.update(ordered);

        return "redirect:order";
    }
}
