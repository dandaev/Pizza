package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.repository.PizzaRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PizzaServiceImpl.class);

    private final PizzaRepository pizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public boolean addPizza(Pizza pizza) {
        try {
            pizzaRepository.save(pizza);
            return true;
        }
        catch (Exception e){
            LOGGER.error("CREATE: Pizza not created");
            return false;
        }
    }

    @Override
    public Pizza getPizzaById(String id) {
        try {
            return pizzaRepository.getPizzaById(id);
        }catch (NullPointerException e){
            LOGGER.debug("No pizza found id: ("+id+") ");
            return null;
        }
    }

    @Override
    public List<Pizza> getPizzaList() {
        return pizzaRepository.findAll();
    }

    @Override
    public List<Pizza> findAllById(List<String> id) {
        try {
            return pizzaRepository.findAllById(id);
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public boolean updatePizza(Pizza pizza) {
        if(pizzaRepository.getPizzaById(pizza.getId())==null) {
            LOGGER.error("UPDATE: Pizza mit id:" + pizza.getId() + " ist nicht existiert");
            return false;
        }
        pizzaRepository.save(pizza);
        return true;
    }

    @Override
    public Pizza deletePizzaById(String id) {
        Pizza pizza = pizzaRepository.getPizzaById(id);
        if(pizza == null) {
            LOGGER.error("DELETE: Pizza ist nicht existiert");
            return null;
        }
        pizzaRepository.delete(pizza);
        return pizza;
    }
}
