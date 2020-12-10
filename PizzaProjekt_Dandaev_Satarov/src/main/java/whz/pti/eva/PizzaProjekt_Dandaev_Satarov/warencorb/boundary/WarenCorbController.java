package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.CurrentUser;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.CartIsNotLoggedIn;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form.ItemCreateForm;

import javax.validation.Valid;

@Controller
public class WarenCorbController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarenCorbController.class);

    private final PizzaService pizzaService;
    private final CartService cartService;
    private final CustomerService customerService;

    private final CartIsNotLoggedIn cartIsNotLoggedIn;

    public WarenCorbController(PizzaService pizzaService, CartService cartService, CustomerService customerService, CartIsNotLoggedIn cartIsNotLoggedIn) {
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.customerService = customerService;
        this.cartIsNotLoggedIn = cartIsNotLoggedIn;
    }


    @GetMapping("/warencorb")
    public String getWarenCorb(Model model) {
        try {
            CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
            if (currentUser != null) {
                if (currentUser.getRole()== Role.ADMIN){
                    return "redirect:/customer/all";
                }
                CustomerDto customerDto = customerService.getCustomerById(currentUser.getCustomerId());
                model.addAttribute("currentCustommer", customerDto);
                model.addAttribute("items", cartService.getListOfItems(currentUser.getCustomerId()));
                model.addAttribute("commonPreis", cartService.getCommonPrice(currentUser.getCustomerId()));
            }
            else {
                model.addAttribute("commonPreis", cartIsNotLoggedIn.getCommonPrice());
                model.addAttribute("items", cartIsNotLoggedIn.getItems());
            }
        } catch (NullPointerException e) {
            LOGGER.warn(e.getMessage());
        }
        return "warencorb";
    }

    @RequestMapping(value = "deleteitem", method = RequestMethod.POST)
    public String deleteItem(
            @Valid @ModelAttribute("itemCreateForm")ItemCreateForm form,
            Model model) {
        try {
            CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
            Pizza pizza = pizzaService.getPizzaById(form.getPizza());
            if (currentUser != null) {
                Item item = new Item(pizza,form.getQuantity(),form.getSize());
                item.setId(form.getId());
                cartService.deleteItemFromCart(currentUser.getCustomerId(),item);
            }
            else {
                cartIsNotLoggedIn.deleteItem(new Item(pizza,form.getQuantity(),form.getSize()));
            }
        } catch (NullPointerException e) {
            LOGGER.warn(e.getMessage());
        }
        return "redirect:warencorb";
    }
    @RequestMapping(value = "increasecount", method = RequestMethod.POST)
    public String increaseItemCount(
            @Valid @ModelAttribute("itemCreateForm")ItemCreateForm form,
            Model model) {
        try {
            CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
            Pizza pizza = pizzaService.getPizzaById(form.getPizza());
            if (currentUser != null) {
                Item item = new Item(pizza,form.getQuantity(),form.getSize());
                item.setId(form.getId());
                cartService.increaseItemCount(currentUser.getCustomerId(),item);
            }
            else {
                cartIsNotLoggedIn.increaseDecreaseCount(new Item(pizza,form.getQuantity(),form.getSize()),0);
            }
        } catch (NullPointerException e) {
            LOGGER.warn(e.getMessage());
        }

        return "redirect:warencorb";
    }

    @RequestMapping(value = "decreasecount", method = RequestMethod.POST)
    public String decreaseItemCount(
            @Valid @ModelAttribute("itemCreateForm")ItemCreateForm form,
            Model model) {
        try {
            CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
            Pizza pizza = pizzaService.getPizzaById(form.getPizza());
            if (currentUser != null) {
                Item item = new Item(pizza,form.getQuantity(),form.getSize());
                item.setId(form.getId());
                cartService.decreaseItemCount(currentUser.getCustomerId(),item);
            }
            else {
                cartIsNotLoggedIn.increaseDecreaseCount(new Item(pizza,form.getQuantity(),form.getSize()),1);
            }
        } catch (NullPointerException e) {
            LOGGER.warn(e.getMessage());
        }
        return "redirect:warencorb";
    }

}
