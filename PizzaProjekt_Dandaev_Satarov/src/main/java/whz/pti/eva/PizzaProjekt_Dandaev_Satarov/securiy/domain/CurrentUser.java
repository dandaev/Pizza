package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User{

    private final User user;

    public CurrentUser(User user){
        super(user.getLoginName(),user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public String getId(){
        return user.getId();
    }

    public String getLoginName(){
        return user.getLoginName();
    }

    public Role getRole(){
        return user.getRole();
    }

    public String getCustomerId(){
        return user.getCustomer().getId();
    }
}
