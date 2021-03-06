package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.OrderedItem;

import java.util.List;

@Service
public interface OrderedItemService {
    OrderedItem createOrderedItem(OrderedItem item);


    OrderedItem getItemById(String id);


    OrderedItem updateItem(OrderedItem item,OrderedItem updatedItem);

    OrderedItem deleteItemById(String id);
}
