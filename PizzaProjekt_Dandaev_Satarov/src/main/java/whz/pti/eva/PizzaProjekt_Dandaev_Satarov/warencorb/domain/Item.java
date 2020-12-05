package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

@Entity
public class Item extends BaseEntity<String> {
    @ManyToOne
    private Pizza pizza;
    private Integer quantity;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return quantity == item.quantity &&
                Objects.equals(pizza, item.pizza) &&
                size == item.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pizza, size);
    }
}
