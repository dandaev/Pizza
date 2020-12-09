package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.Validator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.DeliveryAddressUpdateForm;

@Component
public class DeliveryAddressUpdateFormValidator implements Validator {

    private final DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressUpdateFormValidator(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DeliveryAddressUpdateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeliveryAddressUpdateForm form = (DeliveryAddressUpdateForm) target;
        validateAddressFound(errors, form);
    }

    private void validateAddressFound(Errors errors, DeliveryAddressUpdateForm form) {
        if (!deliveryAddressService.existsById(form.getId())) {
            errors.reject("adddress", "address not found");
        }
    }
}
