package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class InitDb {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDb.class);

    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final DeliveryAddressService deliveryAddressService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitDb(PizzaService pizzaService, CustomerService customerService, DeliveryAddressService deliveryAddressService, UserService userService, PasswordEncoder passwordEncoder) {
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.deliveryAddressService = deliveryAddressService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
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
        sam.setPasswordHash(passwordEncoder.encode("secmusret"));
        sam = customerService.create(sam);

        CustomerDto klopp = new CustomerDto();
        klopp.setFirstName("Jurgen");
        klopp.setLastName("Klopp");
        klopp.setLoginName("jurgenklopp");
        klopp.setPasswordHash(passwordEncoder.encode("secfutret"));
        klopp = customerService.create(klopp);

        UserDto samDto = new UserDto();
        samDto.setCustomerDto(sam);
        samDto.setRole(Role.ADMIN);
        userService.create(samDto);

        UserDto kloppUser = new UserDto();
        kloppUser.setCustomerDto(klopp);
        kloppUser.setRole(Role.USER);
        userService.create(kloppUser);

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
