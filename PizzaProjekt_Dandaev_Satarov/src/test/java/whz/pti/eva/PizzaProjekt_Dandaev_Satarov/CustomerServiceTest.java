package whz.pti.eva.PizzaProjekt_Dandaev_Satarov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository.CustomerRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository.DeliveryAddressRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.repository.UserRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired private CustomerService customerService;

    @Autowired private CustomerRepository customerRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        customerRepository.deleteAll();
    }

    @Test
    void createCustomersTest(){
        CustomerDto bnutz = new CustomerDto();
        bnutz.setFirstName("Todd");
        bnutz.setLastName("King");
        bnutz.setLoginName("bnutz");
        bnutz.setPasswordHash(passwordEncoder.encode("n1"));
        customerService.create(bnutz);

        CustomerDto cnutz = new CustomerDto();
        cnutz.setFirstName("Jurgen");
        cnutz.setLastName("Klopp");
        cnutz.setLoginName("cnutz");
        cnutz.setPasswordHash(passwordEncoder.encode("n2"));
        customerService.create(cnutz);

        assertEquals(2, customerRepository.findAll().size());

        assertNotEquals("n1", customerRepository.findAll().get(0).getPasswordHash());
        assertNotEquals("n2", customerRepository.findAll().get(1).getPasswordHash());
    }
}
