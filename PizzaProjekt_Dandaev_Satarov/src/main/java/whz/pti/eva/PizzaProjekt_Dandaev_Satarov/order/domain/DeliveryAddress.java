package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class DeliveryAddress extends BaseEntity<String> {
    private String street;
    private String houseNumber;
    private String town;
    private String postalCode;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    public DeliveryAddress() {
    }

    public DeliveryAddress(String street, String houseNumber, String town, String postalCode, Customer customer) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
        this.postalCode = postalCode;
        this.customer = customer;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
