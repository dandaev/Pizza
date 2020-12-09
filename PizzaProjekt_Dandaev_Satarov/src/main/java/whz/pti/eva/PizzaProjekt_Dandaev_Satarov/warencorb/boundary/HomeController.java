package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.CurrentUserUtil;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.CurrentUser;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.CartIsNotLoggedIn;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.form.ItemCreateForm;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.validator.ItemCreateFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final CartService cartService;

    private final CartIsNotLoggedIn cartIsNotLoggedIn;
    private final ItemCreateFormValidator itemCreateFormValidator;

    @Autowired
    public HomeController(PizzaService pizzaService, CustomerService customerService, CartService cartService, CartIsNotLoggedIn cartIsNotLoggedIn, ItemCreateFormValidator itemCreateFormValidator) {
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.cartService = cartService;
        this.cartIsNotLoggedIn = cartIsNotLoggedIn;
        this.itemCreateFormValidator = itemCreateFormValidator;
    }

    @InitBinder("itemCreateForm")
    public void initItemCreateFormBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(itemCreateFormValidator);
    }

    @GetMapping("/")
    public String getHome(Model model) {
        try {
            CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
            if (currentUser != null) {
                if (currentUser.getRole()== Role.ADMIN){
                    return "redirect:/customer/all";
                }
                CustomerDto customerDto = customerService.getCustomerById(currentUser.getCustomerId());
                model.addAttribute("currentCustommer", customerDto);
                model.addAttribute("countInWarencorb", cartService.getCommonCount(currentUser.getCustomerId()));
                model.addAttribute("commonPriceInWarencorb", cartService.getCommonPrice(currentUser.getCustomerId()));
            }
            else {
                model.addAttribute("countInWarencorb", cartIsNotLoggedIn.getCount());
                model.addAttribute("commonPriceInWarencorb", cartIsNotLoggedIn.getCommonPrice());
            }
            model.addAttribute("pizzas", pizzaService.getPizzaList());
        } catch (NullPointerException e) {
            LOGGER.warn(e.getMessage());
        }

        return "home";
    }

    @RequestMapping(value = "indenwarencorb", method = RequestMethod.POST)
    public String inDenWarenCorb(
            HttpServletRequest request,
            @Valid @ModelAttribute("itemCreateForm") ItemCreateForm form,
            BindingResult bindingResult,
            Model model) {
        String referer = request.getHeader("Referer");
        if (bindingResult.hasErrors()) {
            return "redirect:" + referer;
        }
        LOGGER.debug(form.getPizza());
        try {
            CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
            Pizza pizza = pizzaService.getPizzaById(form.getPizza());
            if (currentUser == null) {
                cartIsNotLoggedIn.addItem(new Item(pizza,form.getQuantity(),form.getSize()));
            } else {
                Item item = new Item(pizza,form.getQuantity(), form.getSize());
                cartService.addItemToCart(currentUser.getCustomerId(),item);
            }
        } catch (NullPointerException e) {
            LOGGER.warn(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
