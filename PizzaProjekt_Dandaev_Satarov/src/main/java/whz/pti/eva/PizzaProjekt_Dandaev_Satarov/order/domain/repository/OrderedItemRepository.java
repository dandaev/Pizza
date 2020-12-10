package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.OrderedItem;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, String> {

    OrderedItem getOrderedItemById(String id);

    OrderedItem deleteOrderedItemById(String id);

}
