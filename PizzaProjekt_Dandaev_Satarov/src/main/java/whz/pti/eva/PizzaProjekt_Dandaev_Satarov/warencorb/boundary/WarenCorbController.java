package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.CartIsNotLoggedIn;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.PizzaSize;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.form.ItemCreateForm;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
            @Valid @ModelAttribute("itemCreateForm")ItemCreateForm form) {
        Pizza pizza = pizzaService.getPizzaById(form.getPizza());
        cartIsNotLoggedIn.deleteItem(new Item(pizza,form.getQuantity(),form.getSize()));
        return "redirect:warencorb";
    }
    @RequestMapping(value = "increasecount", method = RequestMethod.POST)
    public String increaseItemCount(
            @Valid @ModelAttribute("itemCreateForm")ItemCreateForm form) {
        Pizza pizza = pizzaService.getPizzaById(form.getPizza());
        cartIsNotLoggedIn.increaseDecreaseCount(new Item(pizza,form.getQuantity(),form.getSize()),0);
        return "redirect:warencorb";
    }

    @RequestMapping(value = "decreasecount", method = RequestMethod.POST)
    public String decreaseItemCount(
            @Valid @ModelAttribute("itemCreateForm")ItemCreateForm form) {
        Pizza pizza = pizzaService.getPizzaById(form.getPizza());
        cartIsNotLoggedIn.increaseDecreaseCount(new Item(pizza,form.getQuantity(),form.getSize()),1);
        return "redirect:warencorb";
    }

}
