package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Item extends BaseEntity<String> {
    @ManyToOne
    private Pizza pizza;
    private int quantity;
    private PizzaSize size;

    public Item() {
    }

    public Item(Pizza pizza, int quantity, PizzaSize size) {
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
