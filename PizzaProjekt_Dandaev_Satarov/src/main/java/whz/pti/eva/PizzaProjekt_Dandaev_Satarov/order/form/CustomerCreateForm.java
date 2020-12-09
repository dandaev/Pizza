package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerCreateForm {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 18)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 24)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 18)
    private String loginName;
    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String passwordNew;
    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String passwordConfirm;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
