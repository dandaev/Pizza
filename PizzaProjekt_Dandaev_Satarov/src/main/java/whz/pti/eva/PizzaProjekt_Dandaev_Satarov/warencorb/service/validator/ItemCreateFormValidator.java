package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.Validator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.ItemService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.form.ItemCreateForm;

@Component
public class ItemCreateFormValidator implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCreateFormValidator.class);

    private final ItemService itemService;

    @Autowired
    public ItemCreateFormValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ItemCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ItemCreateForm form = (ItemCreateForm) target;
        System.out.println("Menge: "+ form.getQuantity()+"\nPizzaSize: "+ form.getSize()+"\nId: "+ form.getId());
        validateMenge(errors,form);
    }

    private void validateMenge(Errors error, ItemCreateForm itemCreateForm){
        try {
            int commonQuantity =itemService.getItemById(itemCreateForm.getId()).getQuantity()+ itemCreateForm.getQuantity();
            int maxValue = 100 - itemService.getItemById(itemCreateForm.getId()).getQuantity();
            if (commonQuantity>100){
                error.reject("menge","Menge ist hoch, bitte geben Sie weniger als "+ maxValue);
            }
        }catch (NullPointerException e){
            LOGGER.warn("Session cart");
        }

    }
}
