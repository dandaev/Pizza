package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer getCustomerById(String id);
    boolean existsById(String s);
    boolean existsByLoginName(String s);
}
