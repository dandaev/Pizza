package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Cart extends BaseEntity<String> {
    private int quantity;
    @OneToMany
    private Map<String, Item> items;
    @OneToOne
    private Customer userId;

    public Cart() {
    }

    public Cart(int quantity, Map<String, Item> items, Customer userId) {
        this.quantity = quantity;
        this.items = items;
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public Customer getUserId() {
        return userId;
    }

    public void setUserId(Customer userId) {
        this.userId = userId;
    }
}
