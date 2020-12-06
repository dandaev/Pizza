package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository.CustomerRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerNotDtoById(String id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        Customer customer = customerRepository.getOne(id);
        return toDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            customerDtos.add(toDto(customer));
        }
        return customerDtos;
    }

    @Override
    public boolean existsById(String id) {
        return customerRepository.existsById(id);
    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return customerRepository.existsByLoginName(loginName);
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = fromDto(customerDto);
        return toDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        if (existsById(customerDto.getId())) {
            Customer customer = customerRepository.save(fromDto(customerDto));
            return toDto(customer);
        } else {
            return null;
        }
    }

    @Override
    public void delete(CustomerDto customerDto) {
        customerRepository.delete(fromDto(customerDto));
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setLoginName(customer.getLoginName());
        customerDto.setPasswordHash(customer.getPasswordHash());

        return customerDto;
    }

    @Override
    public Customer fromDto(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setLoginName(customerDto.getLoginName());
        customer.setPasswordHash(customerDto.getPasswordHash());

        return customer;
    }
}
