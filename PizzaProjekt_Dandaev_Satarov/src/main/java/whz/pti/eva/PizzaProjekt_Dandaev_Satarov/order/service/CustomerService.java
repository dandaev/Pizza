package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    Customer getCustomerNotDtoById(String id);
    CustomerDto getCustomerById(String id);
    List<CustomerDto> getAllCustomers();
    boolean existsById(String id);
    boolean existsByLoginName(String loginName);

    CustomerDto create(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto);
    void delete(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);
    Customer fromDto(CustomerDto customerDto);
}
