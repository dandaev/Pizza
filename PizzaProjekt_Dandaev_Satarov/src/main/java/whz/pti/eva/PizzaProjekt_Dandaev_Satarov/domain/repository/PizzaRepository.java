package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain.Pizza;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {
    Pizza getPizzaById(String id);
    List<Pizza> findAll();
}
