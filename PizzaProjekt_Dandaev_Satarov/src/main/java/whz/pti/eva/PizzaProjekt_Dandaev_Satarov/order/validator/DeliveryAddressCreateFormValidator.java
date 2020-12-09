package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.Validator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.DeliveryAddressCreateForm;

@Component
public class DeliveryAddressCreateFormValidator implements Validator {

    private final CustomerService customerService;

    @Autowired
    public DeliveryAddressCreateFormValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DeliveryAddressCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeliveryAddressCreateForm form = (DeliveryAddressCreateForm) target;
        validateCustomerExists(errors ,form);
    }

    private void validateCustomerExists(Errors errors, DeliveryAddressCreateForm form) {
        if (!customerService.existsById(form.getCustomerId())) {
            errors.reject("customer", "customer with this id not found");
        }
    }
}
