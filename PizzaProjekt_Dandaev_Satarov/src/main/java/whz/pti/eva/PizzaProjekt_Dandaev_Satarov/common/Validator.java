package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common;

import org.springframework.validation.Errors;

public interface Validator extends org.springframework.validation.Validator {
    boolean supports(Class<?> clazz);
    void validate(Object target, Errors errors);
}
