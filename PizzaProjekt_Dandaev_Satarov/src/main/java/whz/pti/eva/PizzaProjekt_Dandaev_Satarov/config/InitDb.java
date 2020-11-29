package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.service.PizzaService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class InitDb {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDb.class);

    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final DeliveryAddressService deliveryAddressService;

    @Autowired
    public InitDb(PizzaService pizzaService, CustomerService customerService, DeliveryAddressService deliveryAddressService) {
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.deliveryAddressService = deliveryAddressService;
    }

    @PostConstruct
    public void init() {
        Pizza margarita = new Pizza("Margarita",new BigDecimal("2.50"),new BigDecimal("2.00"),new BigDecimal("1.50"));
        Pizza tonno = new Pizza("Tonno",new BigDecimal("6.00"),new BigDecimal("5.50"),new BigDecimal("5.00"));
        Pizza veggie = new Pizza("Veggie",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        Pizza mozarella = new Pizza("Mozarella",new BigDecimal("4.00"),new BigDecimal("3.50"),new BigDecimal("3.00"));
        Pizza spinat = new Pizza("Spinat",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        Pizza napoli = new Pizza("Napoli",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        Pizza peperoni = new Pizza("Peperoni",new BigDecimal("3.50"),new BigDecimal("3.00"),new BigDecimal("2.50"));
        pizzaService.addPizza(margarita);
        pizzaService.addPizza(tonno);
        pizzaService.addPizza(veggie);
        pizzaService.addPizza(mozarella);
        pizzaService.addPizza(spinat);
        pizzaService.addPizza(napoli);
        pizzaService.addPizza(peperoni);

        CustomerDto sam = new CustomerDto();
        sam.setFirstName("Sam");
        sam.setLastName("Smith");
        sam.setLoginName("samsmith");
        sam.setPasswordHash("secmusret");
        customerService.create(sam);

        CustomerDto klopp = new CustomerDto();
        klopp.setFirstName("Jurgen");
        klopp.setLastName("Klopp");
        klopp.setLoginName("jurgenklopp");
        klopp.setPasswordHash("secfutret");
        customerService.create(klopp);

        DeliveryAddressDto da = new DeliveryAddressDto();
        da.setStreet("Innere Schneeberger Str");
        da.setHouseNumber("23");
        da.setTown("Zwickau");
        da.setPostalCode("08056");
        da.setCustomer(
                customerService.getAllCustomers().get(0)
        );
        DeliveryAddressDto da2 = new DeliveryAddressDto();
        da2.setStreet("Marien Str");
        da2.setHouseNumber("14");
        da2.setTown("Dresden");
        da2.setPostalCode("04512");
        da2.setCustomer(
                customerService.getAllCustomers().get(0)
        );
        DeliveryAddressDto da3 = new DeliveryAddressDto();
        da3.setStreet("Katharinen Str");
        da3.setHouseNumber("109");
        da3.setTown("Dortmund");
        da3.setPostalCode("00184");
        da3.setCustomer(
                customerService.getAllCustomers().get(1)
        );
        deliveryAddressService.create(da);
        deliveryAddressService.create(da2);
        deliveryAddressService.create(da3);
    }
}
