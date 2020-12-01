package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerUpdateForm {
    @NotNull
    private String id;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 18)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 24)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 18)
    private String loginName;
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 24)
    private String passwordNew;
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 24)
    private String passwordConfirm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
