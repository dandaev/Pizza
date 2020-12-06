package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Cart extends BaseEntity<String> {
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "cart_items",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    @MapKey(name = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Map<String, Item> items;

    @OneToOne
    private Customer user;

    public Cart() {
    }

    public Cart(int quantity, Map<String, Item> items, Customer user) {
        this.quantity = quantity;
        this.items = items;
        this.user = user;
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

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}
