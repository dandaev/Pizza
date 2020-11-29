package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Ordered;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, String> {
}
