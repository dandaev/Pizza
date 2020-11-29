package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.implementation.ItemServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@SessionScope
public class CartIsNotLoggedIn {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartIsNotLoggedIn.class);

    private final List<Item> items = new ArrayList<>();

    private final PizzaService pizzaService;

    public CartIsNotLoggedIn(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    public List<Item> getItems() {
        return items;
    }

    public Integer getCount() {
        int count = 0;
        for (Item item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    public BigDecimal getCommonPrice() {
        BigDecimal commonPrice = BigDecimal.ZERO;
        for (Item item : items) {
            Pizza pizza = pizzaService.getPizzaById(item.getPizza().getId());
            switch (item.getSize()) {
                case Small:
                    commonPrice = commonPrice.add(pizza.getPriceSmall().multiply(new BigDecimal(item.getQuantity())));
                    LOGGER.info("Added Small Pizza: " + pizza.getName()
                            + " Price " + pizza.getPriceSmall()
                            + " Count " + item.getQuantity()
                            + " Common price " + commonPrice);
                    break;
                case Large:
                    commonPrice = commonPrice.add(pizza.getPriceLarge().multiply(new BigDecimal(item.getQuantity())));
                    LOGGER.info("Added Large Pizza: " + pizza.getName()
                            + " Price " + pizza.getPriceLarge()
                            + " Count " + item.getQuantity()
                            + " Common price " + commonPrice);
                    break;
                case Medium:
                    commonPrice = commonPrice.add(pizza.getPriceMedium().multiply(new BigDecimal(item.getQuantity())));
                    LOGGER.info("Added Medium Pizza: " + pizza.getName()
                            + " Price " + pizza.getPriceMedium()
                            + " Count " + item.getQuantity()
                            + " Common price " + commonPrice);
                    break;
            }
        }
        return commonPrice;
    }

    public void deleteItem(Item item) {
        LOGGER.info("Zashlo v deleteItem " + item);
        int r = -1;
        for (int i=0;i<=items.size();i++){
            if (items.get(i).getPizza().equals(item.getPizza()) &&
                    items.get(i).getSize().equals(item.getSize()) &&
                        items.get(i).getQuantity() == item.getQuantity()) {
                r=i;
                break;
            }
        }
        if (r != -1){
            items.remove(r);
        }
    }

    public void addItem(Item item) {
        int a = 0;
        boolean g = true;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getPizza().equals(item.getPizza()) &&
                    items.get(i).getSize().equals(item.getSize())) {
                a = i;
                g = false;
                break;
            }
        }
        if (g) {
            LOGGER.info("New Item created");
            items.add(item);
        } else {
            LOGGER.info("Quantity added");
            item.setQuantity(items.get(a).getQuantity() + item.getQuantity());
            items.set(a, item);
        }
    }
}