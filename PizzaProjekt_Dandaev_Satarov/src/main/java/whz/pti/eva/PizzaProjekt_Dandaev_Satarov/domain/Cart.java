package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain;

import org.hibernate.annotations.GenericGenerator;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain.Item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Map;

@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private int quantity;
    @OneToMany
    private Map<String, Item> items;

    public Cart() {
    }

    public Cart(int quantity, Map<String, Item> items) {
        this.quantity = quantity;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
