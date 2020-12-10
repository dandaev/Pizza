package whz.pti.eva.PizzaProjekt_Dandaev_Satarov;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.Role;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.domain.User;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.UserService;

import java.util.List;
import java.util.Optional;

@MockMvcTest
public class GetCustomersListTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private CustomerService customerService;
    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();

        CustomerDto bnutz = new CustomerDto();
        bnutz.setId("45c9709d-53bb-4ec0-9ab5-59f24f60652a");
        bnutz.setFirstName("Todd");
        bnutz.setLastName("King");
        bnutz.setLoginName("bnutz");
        bnutz.setPasswordHash("n1");
        User buser = new User();
        buser.setId("66e16da5-9fe4-4323-b6a4-d17de9cc4805");
        buser.setLoginName("bnutz");
        buser.setPasswordHash("n1");
        buser.setRole(Role.USER);
        buser.setCustomer(customerService.fromDto(bnutz));

        CustomerDto cnutz = new CustomerDto();
        cnutz.setId("f7871e6c-6ec2-4425-8b63-0e05f6a074b3");
        cnutz.setFirstName("Elin");
        cnutz.setLastName("Harrison");
        cnutz.setLoginName("cnutz");
        cnutz.setPasswordHash("n2");
        User cuser = new User();
        cuser.setId("d7bd2d06-c299-454d-95cd-39f8711114d2");
        cuser.setLoginName("cnutz");
        cuser.setPasswordHash("n2");
        cuser.setRole(Role.USER);
        cuser.setCustomer(customerService.fromDto(cnutz));

        when(customerService.getAllCustomers())
                .thenReturn(List.of(
                        bnutz,
                        cnutz
                ));

        User admin = new User();
        admin.setId("a14d3fae-7a6b-4555-825f-6e0552013008");
        admin.setLoginName("admin");
        admin.setPasswordHash("a1");
        admin.setRole(Role.ADMIN);

        when(userService.getUserByLoginName("admin")).thenReturn(Optional.of(admin));

        when(userService.getUserByLoginName("bnutz")).thenReturn(Optional.of(buser));
        when(userService.getUserByLoginName("cnutz")).thenReturn(Optional.of(cuser));
    }

//    @Test
//    public void getAllCustomersWithoutPermission() throws Exception {
//        mockMvc.perform(get("/customer/all"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//
//    @Test
//    public void getAllCustomersWithUserPermission() throws Exception {
//        UsernamePasswordAuthenticationToken principal =
//
//        mockMvc
//                .perform()
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getAllCustomersWithAdminPermission() {}
}
