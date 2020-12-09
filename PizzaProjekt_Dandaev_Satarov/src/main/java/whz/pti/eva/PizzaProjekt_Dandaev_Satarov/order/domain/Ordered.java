package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Ordered extends BaseEntity<String> {
    private int numberOfItems;
    @OneToMany
    private List<OrderedItem> orderedItems;

    @OneToOne
    private Customer userId;

    public Ordered() {
    }

    public Ordered(int numberOfItems, List<OrderedItem> orderedItems, Customer userId) {
        this.numberOfItems = numberOfItems;
        this.orderedItems = orderedItems;
        this.userId = userId;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Customer getUserId() {
        return userId;
    }

    public void setUserId(Customer userId) {
        this.userId = userId;
    }
}
