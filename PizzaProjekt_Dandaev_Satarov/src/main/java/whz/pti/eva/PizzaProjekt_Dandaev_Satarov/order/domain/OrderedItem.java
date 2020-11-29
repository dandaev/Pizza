package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.PizzaSize;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OrderedItem extends BaseEntity<String> {
    @ManyToOne
    private Pizza pizza;
    private int quantity;
    private PizzaSize size;

    public OrderedItem() {
    }

    public OrderedItem(Pizza pizza, int quantity, PizzaSize size) {
        this.pizza = pizza;
        this.quantity = quantity;
        this.size = size;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }
}
