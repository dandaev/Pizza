package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface CartService {
    void create(Cart cart);
    Cart addItemToCart(String userId, Item item);

    Cart getCartByUserId(String userId);
    BigDecimal getCommonPrice(String userId);
    Integer getCommonCount(String userId);
    ArrayList<Item> getListOfItems(String userId);
    Item existItemInCart(String userId,Item item);

    Cart update(Cart cart);
    Cart increaseItemCount(String userId, Item item);
    Cart decreaseItemCount(String userId, Item item);

    Cart delete(String userId);
    Cart deleteItemFromCart(String userId, Item item);


}
