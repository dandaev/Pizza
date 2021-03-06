package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.Validator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.CustomerUpdateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.implementation.CustomerServiceImpl;

@Component
public class CustomerUpdateFormValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerService customerService;

    @Autowired
    public CustomerUpdateFormValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CustomerUpdateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerUpdateForm form = (CustomerUpdateForm) target;
        validateLoginName(errors, form);
        validatePasswords(errors, form);
    }

    private void validateLoginName(Errors errors, CustomerUpdateForm form) {
        CustomerDto customerDto = customerService.getCustomerById(form.getId());
        try {
            if (!customerDto.getLoginName().equals(form.getLoginName())) {
                if (customerService.existsByLoginName(form.getLoginName())) {
                    errors.reject("loginName", "loginName existiert");
                }
            }
        } catch (Exception e) {
            errors.reject("error", "unknown error");
        }
    }

    private void validatePasswords(Errors errors, CustomerUpdateForm form) {
        if (!form.getPasswordNew().equals(form.getPasswordConfirm())) {
            errors.reject("password", "passwords do not match");
        }
    }
}
