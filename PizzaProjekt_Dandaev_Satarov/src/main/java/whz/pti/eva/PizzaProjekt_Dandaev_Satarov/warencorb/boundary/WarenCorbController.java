package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String deleteItem(
            @RequestParam("deletedItemPizzaId") String deletedItemPizzaId,
            @RequestParam("deletedItemQuantity") int deletedItemQuantity,
            @RequestParam("deletedItemPizzaSize") PizzaSize deletedItemPizzaSize) {
        Item deletedItem = new Item(pizzaService.getPizzaById(deletedItemPizzaId), deletedItemQuantity, deletedItemPizzaSize);
        cartIsNotLoggedIn.deleteItem(deletedItem);
        return "redirect:warencorb";
    }
    @RequestMapping(value = "increasecount", method = RequestMethod.POST)
    public String increaseItemCount(
            @RequestParam("increasedItemPizzaId") String increasedItemPizzaId,
            @RequestParam("increasedItemQuantity") int increasedItemQuantity,
            @RequestParam("increasedItemPizzaSize") PizzaSize increasedItemPizzaSize) {
        Item increasedItem = new Item(pizzaService.getPizzaById(increasedItemPizzaId), increasedItemQuantity, increasedItemPizzaSize);
        cartIsNotLoggedIn.increaseDecreaseCount(increasedItem,0);
        return "redirect:warencorb";
    }

    @RequestMapping(value = "decreasecount", method = RequestMethod.POST)
    public String decreaseItemCount(
            @RequestParam("decreasedItemPizzaId") String decreasedItemPizzaId,
            @RequestParam("decreasedItemQuantity") int decreasedItemQuantity,
            @RequestParam("decreasedItemPizzaSize") PizzaSize decreasedItemPizzaSize) {
        Item decreaseItem = new Item(pizzaService.getPizzaById(decreasedItemPizzaId), decreasedItemQuantity, decreasedItemPizzaSize);
        cartIsNotLoggedIn.increaseDecreaseCount(decreaseItem,1);
        return "redirect:warencorb";
    }

}
