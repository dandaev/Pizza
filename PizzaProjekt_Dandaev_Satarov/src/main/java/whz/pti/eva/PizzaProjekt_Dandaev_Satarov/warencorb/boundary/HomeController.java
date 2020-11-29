package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.CartIsNotLoggedIn;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.PizzaSize;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class HomeController {

    private final PizzaService pizzaService;

    private final CartIsNotLoggedIn cartIsNotLoggedIn;

    @Autowired
    public HomeController(PizzaService pizzaService, CartIsNotLoggedIn cartIsNotLoggedIn) {
        this.pizzaService = pizzaService;
        this.cartIsNotLoggedIn = cartIsNotLoggedIn;
    }

    @GetMapping("/")
    public String getHome(Model model){ //, HttpSession session) {
        /*List<String> warencorb = (List<String>) session.getAttribute("WARENCORB");
        System.out.println("ut");
        if (warencorb == null) {
            warencorb = new ArrayList<>();
        }
        model.addAttribute("warenCorb", warencorb);*/
        model.addAttribute("countInWarencorb", cartIsNotLoggedIn.getCount());
        model.addAttribute("commonPriceInWarencorb", cartIsNotLoggedIn.getCommonPrice());
        model.addAttribute("pizzas", pizzaService.getPizzaList());
        return "home";
    }

    @RequestMapping(value = "indenwarencorb", method = RequestMethod.POST)
    public String inDenWarenCorb(@RequestParam("menge") Integer menge,
                                 @RequestParam("pizza") String pizza,
                                 @RequestParam("pizzaSize") PizzaSize pizzaSize){
                                 //, HttpServletRequest request) {

/*        List<Item> warenCorb = (List<Item>) request.getSession().getAttribute("WARENCORB");
        if (warenCorb == null) {
            warenCorb = new ArrayList<>();
            request.getSession().setAttribute("WARENCORB", warenCorb);
        }
        warenCorb.add(new Item(pizzaService.getPizzaById(pizza), menge, pizzaSize));
        request.getSession().setAttribute("WARENCORB", warenCorb);*/

        cartIsNotLoggedIn.addItem(new Item(pizzaService.getPizzaById(pizza),menge,pizzaSize));
        return "redirect:/";
    }
}
