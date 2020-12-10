package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Ordered;
import java.util.List;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, String> {

//    @Query("select Ordered from ")
//    Ordered getOrderedByUserIdAndDelivered(String id,boolean delivered);
    List<Ordered> findByUserIdAndDeliveredNot(Customer userId, boolean delivered);

//    List<Ordered> findByUserIdAndDeliveredNot(Customer userId, boolean delivered);
}
