package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.form;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.PizzaSize;

import javax.validation.constraints.*;

public class ItemCreateForm {

    private String id;

//    @NotNull(message = "Bitte geben Sie die Menge")
//    @Positive
//    @Max(value = 100,message = "Menge soll nicht mehr als 100 sein")
//    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="msg")
    @NotNull
    private String quantity;

    @NotNull
    private String pizza;

    @NotNull
    private PizzaSize size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return Integer.parseInt(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = String.valueOf(quantity);
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }
}
