package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getCustomerById(String id);
    List<CustomerDto> getAllCustomers();
    boolean existsById(String id);

    CustomerDto create(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto);
    void delete(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);
    Customer fromDto(CustomerDto customerDto);
}
