package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto;

import java.math.BigDecimal;

public class Check {
    private String pizzaName;
    private String pizzaSize;
    private int quantity;
    private BigDecimal pizzaPrice;
    private BigDecimal commonPrice;

    public Check(String pizzaName, String pizzaSize, int quantity, BigDecimal pizzaPrice) {
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.quantity = quantity;
        this.pizzaPrice = pizzaPrice;
    }

    public Check() {
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(BigDecimal pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public BigDecimal getCommonPrice() {
        if (pizzaPrice == null){
            return new BigDecimal("0,0");
        }
        this.commonPrice = pizzaPrice.multiply(new BigDecimal(quantity));
        return commonPrice;
    }

    public void setCommonPrice(BigDecimal commonPrice) {
        this.commonPrice = commonPrice;
    }
}
