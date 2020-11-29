package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final DeliveryAddressService deliveryAddressService;

    @Autowired
    public CustomerController(CustomerService customerService, DeliveryAddressService deliveryAddressService) {
        this.customerService = customerService;
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping("/customers")
    public String getCustomersPage(
            Model model
    ) {
        Map<CustomerDto, List<DeliveryAddressDto>> customers = new HashMap<>();
        for (CustomerDto c : customerService.getAllCustomers()) {
            List<DeliveryAddressDto> addresses = new ArrayList<>();
            addresses.addAll(deliveryAddressService.getDeliveryAddressesByCustomer(c));
            customers.put(c, addresses);
        }
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerInfoPage(
            @PathVariable String id,
            Model model
    ) {
        CustomerDto customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer_info";
    }
}
