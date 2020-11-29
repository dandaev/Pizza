package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Ordered extends BaseEntity<String> {
    private int numberOfItems;
    @OneToMany
    private List<OrderedItem> orderedItems;

    public Ordered() {
    }

    public Ordered(int numberOfItems, List<OrderedItem> orderedItems) {
        this.numberOfItems = numberOfItems;
        this.orderedItems = orderedItems;
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
}
