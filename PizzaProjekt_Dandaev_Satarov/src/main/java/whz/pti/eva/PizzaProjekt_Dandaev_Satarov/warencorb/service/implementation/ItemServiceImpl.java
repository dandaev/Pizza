package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.repository.ItemRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(Item item) {
        try {
            return itemRepository.save(item);
        }catch (Exception e){
            LOGGER.error("CREATE: Item not created");
            return null;
        }
    }

    @Override
    public Item getItemById(String id) {
        return itemRepository.getItemById(id);
    }

    @Override
    public List<Item> getItemListByPizzaId(String pizzaId) {
        return itemRepository.getItemsByPizzaId(pizzaId);
    }

    @Override
    public boolean updateItem(Item item) {
        if (itemRepository.getItemById(item.getId()) == null){
            LOGGER.error("UPDATE: Item mit id: "+ item.getId()+" nicht existiert");
            return false;
        }
        itemRepository.save(item);
        return true;
    }

    @Override
    public Item deleteItemById(String id) {
        Item item = itemRepository.getItemById(id);
        if (item == null){
            LOGGER.error("DELETE: Item mit id: "+id+" nicht existiert");
            return null;
        }
        itemRepository.delete(item);
        return item;
    }
}
