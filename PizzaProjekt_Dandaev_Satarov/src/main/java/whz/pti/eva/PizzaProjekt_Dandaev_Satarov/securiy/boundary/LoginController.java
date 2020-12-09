package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.CurrentUser;

import java.util.Optional;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        if (currentUser != null) {
            return "redirect:/";
        }
        return "login";
    }
}
