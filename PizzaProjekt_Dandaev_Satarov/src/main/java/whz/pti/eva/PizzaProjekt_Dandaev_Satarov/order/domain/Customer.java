package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Customer extends BaseEntity<String> {
    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String loginName, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.passwordHash = passwordHash;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
