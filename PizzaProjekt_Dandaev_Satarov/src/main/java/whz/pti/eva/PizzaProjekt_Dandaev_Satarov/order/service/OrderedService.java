package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Ordered;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.OrderedItem;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public interface OrderedService {
    void create(Ordered ordered);
    Ordered addItemToCart(String userId, OrderedItem item);

    Ordered getCartByUserId(String userId);
    BigDecimal getCommonPrice(String userId);
    Integer getCommonCount(String userId);
    ArrayList<Item> getListOfItems(String userId);
    Item existItemInOrdered(String userId,OrderedItem item);

    Ordered update(Cart cart);
    Ordered increaseItemCount(String userId, OrderedItem item);
    Ordered decreaseItemCount(String userId, OrderedItem item);

    Ordered delete(String userId);
    Ordered deleteItemFromOrdered(String userId, OrderedItem item);
}
