package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.service;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain.Item;

import java.util.List;

public interface ItemService {

    boolean createItem(Item item);

    Item getItemById(String id);
    List<Item> getItemListByPizzaId(String pizzaId);

    boolean updateItem(Item item);

    Item deleteItemById(String id);

}
