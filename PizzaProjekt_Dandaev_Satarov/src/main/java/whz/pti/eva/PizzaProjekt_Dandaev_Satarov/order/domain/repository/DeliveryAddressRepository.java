package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.DeliveryAddress;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, String> {
    boolean existsById(String s);
    List<DeliveryAddress> getAllByCustomer(Customer customer);
}
