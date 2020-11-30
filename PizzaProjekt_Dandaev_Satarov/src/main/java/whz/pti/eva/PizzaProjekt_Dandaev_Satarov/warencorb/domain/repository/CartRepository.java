package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
}
