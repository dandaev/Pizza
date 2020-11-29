package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Pizza extends BaseEntity<String> {
    private String name;
    private BigDecimal priceLarge;
    private BigDecimal priceMedium;
    private BigDecimal priceSmall;

    public Pizza() {
    }

    public Pizza(String name, BigDecimal priceLarge, BigDecimal priceMedium, BigDecimal priceSmall) {
        this.name = name;
        this.priceLarge = priceLarge;
        this.priceMedium = priceMedium;
        this.priceSmall = priceSmall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(BigDecimal priceLarge) {
        this.priceLarge = priceLarge;
    }

    public BigDecimal getPriceMedium() {
        return priceMedium;
    }

    public void setPriceMedium(BigDecimal priceMedium) {
        this.priceMedium = priceMedium;
    }

    public BigDecimal getPriceSmall() {
        return priceSmall;
    }

    public void setPriceSmall(BigDecimal priceSmall) {
        this.priceSmall = priceSmall;
    }
}
