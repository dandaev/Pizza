package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Item getItemById(String id);
    List<Item> getItemsByPizzaId(String id);
}
