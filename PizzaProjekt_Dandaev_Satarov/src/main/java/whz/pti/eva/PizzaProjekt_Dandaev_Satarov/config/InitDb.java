package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.User;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.PizzaSize;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;

@Component
public class InitDb {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDb.class);

    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final DeliveryAddressService deliveryAddressService;
    private final UserService userService;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitDb(PizzaService pizzaService, CustomerService customerService, DeliveryAddressService deliveryAddressService, UserService userService, CartService cartService, PasswordEncoder passwordEncoder) {
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.deliveryAddressService = deliveryAddressService;
        this.userService = userService;
        this.cartService = cartService;
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

        UserDto admin = new UserDto();
        admin.setLoginName("admin");
        admin.setPasswordHash(passwordEncoder.encode("a1"));
        admin.setRole(Role.ADMIN);
        userService.create(admin);

        CustomerDto bnutz = new CustomerDto();
        bnutz.setFirstName("Todd");
        bnutz.setLastName("King");
        bnutz.setLoginName("bnutz");
        bnutz.setPasswordHash(passwordEncoder.encode("n1"));
        bnutz = customerService.create(bnutz);
        //
        UserDto buser = new UserDto();
        buser.setCustomerDto(bnutz);
        buser.setRole(Role.USER);
        userService.create(buser);
        //
        Cart bnutzCart = new Cart();
        bnutzCart.setItems(new HashMap<>());
        bnutzCart.setQuantity(0);
        bnutzCart.setUser(customerService.getCustomerNotDtoById(bnutz.getId()));
        cartService.create(bnutzCart);

        CustomerDto cnutz = new CustomerDto();
        cnutz.setFirstName("Elin");
        cnutz.setLastName("Harrison");
        cnutz.setLoginName("cnutz");
        cnutz.setPasswordHash(passwordEncoder.encode("n2"));
        cnutz = customerService.create(cnutz);
        //
        UserDto cuser = new UserDto();
        cuser.setCustomerDto(cnutz);
        cuser.setRole(Role.USER);
        userService.create(cuser);
        //
        Cart cnutzCart = new Cart();
        cnutzCart.setItems(new HashMap<>());
        cnutzCart.setQuantity(0);
        cnutzCart.setUser(customerService.getCustomerNotDtoById(cnutz.getId()));
        cartService.create(cnutzCart);


        DeliveryAddressDto da = new DeliveryAddressDto();
        da.setStreet("Koepenicker Str");
        da.setHouseNumber("31");
        da.setTown("Siesbach");
        da.setPostalCode("55767");
        da.setCustomer(bnutz);
        deliveryAddressService.create(da);

        DeliveryAddressDto da2 = new DeliveryAddressDto();
        da2.setStreet("Mohrenstrasse");
        da2.setHouseNumber("9");
        da2.setTown("Schopfloch");
        da2.setPostalCode("91626");
        da2.setCustomer(bnutz);
        deliveryAddressService.create(da2);

        DeliveryAddressDto da3 = new DeliveryAddressDto();
        da3.setStreet("Anhalter Strasse");
        da3.setHouseNumber("109");
        da3.setTown("Antdorf");
        da3.setPostalCode("67759");
        da3.setCustomer(cnutz);
        deliveryAddressService.create(da3);
    }
}
