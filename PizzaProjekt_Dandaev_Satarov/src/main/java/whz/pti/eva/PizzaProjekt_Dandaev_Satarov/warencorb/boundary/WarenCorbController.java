package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.CartIsNotLoggedIn;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.PizzaSize;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;

@Controller
public class WarenCorbController {

    private final PizzaService pizzaService;

    private final CartIsNotLoggedIn cartIsNotLoggedIn;

    public WarenCorbController(PizzaService pizzaService, CartIsNotLoggedIn cartIsNotLoggedIn) {
        this.pizzaService = pizzaService;
        this.cartIsNotLoggedIn = cartIsNotLoggedIn;
    }

    @GetMapping("/warencorb")
    public String getWarenCorb(Model model) {
        model.addAttribute("commonPreis", cartIsNotLoggedIn.getCommonPrice());
        model.addAttribute("items", cartIsNotLoggedIn.getItems());
        return "warencorb";
    }

    @RequestMapping(value = "deleteitem", method = RequestMethod.POST)
    public String deleteItem(@RequestParam("deletedItemPizzaId") String deletedItemPizzaId,
                             @RequestParam("deletedItemQuantity") int deletedItemQuantity,
                             @RequestParam("deletedItemPizzaSize") PizzaSize deletedItemPizzaSize) {
        Item deletedItem = new Item(pizzaService.getPizzaById(deletedItemPizzaId), deletedItemQuantity,deletedItemPizzaSize );
        cartIsNotLoggedIn.deleteItem(deletedItem);
        return "redirect:warencorb";
    }
}
