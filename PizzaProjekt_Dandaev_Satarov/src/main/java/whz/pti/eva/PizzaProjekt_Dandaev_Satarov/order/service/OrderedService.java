package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.DeliveryAddress;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Ordered;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.OrderedItem;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.Check;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface OrderedService {
    void create(Ordered ordered);
    Ordered addItemToOrdered(String userId, OrderedItem item);
    Ordered convertCartToOrdered(String userId);

    Ordered getOrderedByUserId(String userId);
    BigDecimal getCommonPrice(Ordered ordered);
    Integer getCommonCount(Ordered ordered);
    List<Check> getCheckOfItems(Ordered ordered);

    Ordered update(Ordered ordered);
    Ordered setDeliveryAddress(Ordered ordered, DeliveryAddress deliveryAddress);
    Ordered increaseItemCount(String userId, OrderedItem item);
    Ordered decreaseItemCount(String userId, OrderedItem item);
    OrderedItem existItemInOrdered(List<OrderedItem> orderedItems,OrderedItem item);

    Ordered delete(String userId);
    Ordered deleteItemFromOrdered(String userId, OrderedItem item);
}
