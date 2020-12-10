package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.OrderedItem;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository.OrderedItemRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.OrderedItemService;

import java.util.List;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedItemServiceImpl.class);

    private final OrderedItemRepository orderedItemRepository;

    public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository) {
        this.orderedItemRepository = orderedItemRepository;
    }


    @Override
    public OrderedItem createOrderedItem(OrderedItem item) {

        return orderedItemRepository.save(item);
    }

    @Override
    public OrderedItem getItemById(String id) {

        return orderedItemRepository.getOrderedItemById(id);
    }

    @Override
    public OrderedItem updateItem(OrderedItem item, OrderedItem updatedItem) {
        OrderedItem orderedItem = orderedItemRepository.getOrderedItemById(item.getId());
        orderedItem.setUserId(updatedItem.getUserId());
        orderedItem.setSize(updatedItem.getSize());
        orderedItem.setQuantity(updatedItem.getQuantity());
        orderedItem.setPizza(updatedItem.getPizza());
        return orderedItemRepository.save(orderedItem);
    }

    @Override
    public OrderedItem deleteItemById(String id) {
        return orderedItemRepository.deleteOrderedItemById(id);
    }
}
