package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ordered extends BaseEntity<String> {

    private int numberOfItems;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "ordered_ordereditems",
            joinColumns = {@JoinColumn(name = "ordered_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ordereditem_id", referencedColumnName = "id")})
    @MapKey(name = "id")
    private List<OrderedItem> orderedItems;

    @ManyToOne
    private Customer userId;

    @ManyToOne
    private DeliveryAddress deliveryAddress;

    private boolean delivered;

    public Ordered() {
    }

    public Ordered(int numberOfItems, List<OrderedItem> orderedItems, Customer userId, boolean delivered, DeliveryAddress deliveryAddress) {
        this.numberOfItems = numberOfItems;
        this.orderedItems = orderedItems;
        this.userId = userId;
        this.deliveryAddress = deliveryAddress;
        this.delivered = delivered;
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

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
