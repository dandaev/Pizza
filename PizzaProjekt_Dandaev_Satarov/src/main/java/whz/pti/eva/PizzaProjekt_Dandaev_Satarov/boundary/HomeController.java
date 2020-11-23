package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.service.ItemService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.service.PizzaService;

@Controller
public class HomeController {

    private final PizzaService pizzaService;

    @Autowired
    public HomeController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("pizzas",pizzaService.getPizzaList());
        return "home";
    }
}
