package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;

import java.util.List;

public interface PizzaService {

    boolean addPizza(Pizza pizza);

    Pizza getPizzaById(String id);
    List<Pizza> getPizzaList();

    boolean updatePizza(Pizza pizza);

    Pizza deletePizzaById(String id);
}
