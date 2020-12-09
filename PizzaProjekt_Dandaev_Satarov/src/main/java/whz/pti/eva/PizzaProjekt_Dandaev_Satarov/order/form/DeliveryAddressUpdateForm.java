package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeliveryAddressUpdateForm {
    @NotNull
    private String id;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 64)
    private String street;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 16)
    private String houseNumber;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 64)
    private String town;
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 12)
    private String postalCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
