package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.Validator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.CustomerCreateForm;

@Component
public class CustomerCreateFormValidator implements Validator {
    private final CustomerService customerService;

    @Autowired
    public CustomerCreateFormValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CustomerCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerCreateForm form = (CustomerCreateForm) target;
        validateLoginName(errors, form);
        validatePasswords(errors, form);
    }

    private void validateLoginName(Errors errors, CustomerCreateForm form) {
        if (customerService.existsByLoginName(form.getLoginName())) {
            errors.reject("loginName", "loginName existiert");
        }
    }

    private void validatePasswords(Errors errors, CustomerCreateForm form) {
        if (!form.getPasswordNew().equals(form.getPasswordConfirm())) {
            errors.reject("password", "passwords do not match");
        }
    }
}
