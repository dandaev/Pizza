package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common;

import org.springframework.ui.Model;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.CurrentUser;

public class CurrentUserUtil {

    public static String getCurrentUser(Model model){
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        String loginName = currentUser.getLoginName();
        model.addAttribute("loginName", loginName);
        return loginName;
    }

}
