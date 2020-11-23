package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.service.ItemService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.service.PizzaService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class InitDb {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDb.class);

    private final PizzaService pizzaService;
    private final ItemService itemService;

    public InitDb(PizzaService pizzaService, ItemService itemService) {
        this.pizzaService = pizzaService;
        this.itemService = itemService;
    }

    @PostConstruct
    public void init() {
        Pizza margarita = new Pizza("Margarita",new BigDecimal("2.50"),new BigDecimal("2.00"),new BigDecimal("1.50"));
        Pizza tonno = new Pizza("Tonno",new BigDecimal("6.00"),new BigDecimal("5.50"),new BigDecimal("5.00"));
        Pizza veggie = new Pizza("Veggie",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        Pizza mozarella = new Pizza("Mozarella",new BigDecimal("4.00"),new BigDecimal("3.50"),new BigDecimal("3.00"));
        Pizza spinat = new Pizza("Spinat",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        Pizza napoli = new Pizza("Napoli",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        Pizza peperoni = new Pizza("Peperoni",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        pizzaService.addPizza(margarita);
        pizzaService.addPizza(tonno);
        pizzaService.addPizza(veggie);
        pizzaService.addPizza(mozarella);
        pizzaService.addPizza(spinat);
        pizzaService.addPizza(napoli);
        pizzaService.addPizza(peperoni);
    }
}
