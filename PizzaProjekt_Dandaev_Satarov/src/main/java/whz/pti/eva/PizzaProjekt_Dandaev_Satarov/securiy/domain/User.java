package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain;

import org.hibernate.annotations.Fetch;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;

import javax.persistence.*;

@Entity
public class User extends BaseEntity<String> {
    @Column(nullable = false, unique = true)
    private String loginName;
    @Column(nullable = false, unique = true)
    private String passwordHash;
    @OneToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @Column(nullable = false)
    private Role role;

    public User() {
    }

    public User(String loginName, String passwordHash, Customer customer, Role role) {
        this.loginName = loginName;
        this.passwordHash = passwordHash;
        this.customer = customer;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
