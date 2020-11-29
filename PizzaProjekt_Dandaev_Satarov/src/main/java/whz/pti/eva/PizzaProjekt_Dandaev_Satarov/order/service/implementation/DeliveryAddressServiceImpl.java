package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.implementation;

import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.DeliveryAddress;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository.DeliveryAddressRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final CustomerService customerService;

    public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository, CustomerService customerService) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.customerService = customerService;
    }

    @Override
    public DeliveryAddressDto getDeliveryAddressById(String id) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.getOne(id);
        return toDto(deliveryAddress);
    }

    @Override
    public List<DeliveryAddressDto> getAllDeliveryAddresses() {
        List<DeliveryAddressDto> deliveryAddressDtos = new ArrayList<>();
        for (DeliveryAddress deliveryAddress : deliveryAddressRepository.findAll()) {
            deliveryAddressDtos.add(toDto(deliveryAddress));
        }
        return deliveryAddressDtos;
    }

    @Override
    public List<DeliveryAddressDto> getDeliveryAddressesByCustomer(CustomerDto customerDto) {
        List<DeliveryAddress> deliveryAddresses =
                deliveryAddressRepository.getAllByCustomer(customerService.fromDto(customerDto));
        List<DeliveryAddressDto> deliveryAddressDtos = new ArrayList<>();
        for (DeliveryAddress deliveryAddress : deliveryAddresses) {
            deliveryAddressDtos.add(toDto(deliveryAddress));
        }
        return deliveryAddressDtos;
    }

    @Override
    public boolean existsById(String id) {
        return deliveryAddressRepository.existsById(id);
    }

    @Override
    public DeliveryAddressDto create(DeliveryAddressDto deliveryAddressDto) {
        DeliveryAddress deliveryAddress = fromDto(deliveryAddressDto);
        return toDto(deliveryAddressRepository.save(deliveryAddress));
    }

    @Override
    public DeliveryAddressDto update(DeliveryAddressDto deliveryAddressDto) {
        if (existsById(deliveryAddressDto.getId())) {
            DeliveryAddress deliveryAddress = deliveryAddressRepository.save(fromDto(deliveryAddressDto));
            return toDto(deliveryAddress);
        } else {
            return null;
        }
    }

    @Override
    public void delete(DeliveryAddressDto deliveryAddressDto) {
        deliveryAddressRepository.delete(fromDto(deliveryAddressDto));
    }

    @Override
    public DeliveryAddressDto toDto(DeliveryAddress deliveryAddress) {
        DeliveryAddressDto deliveryAddressDto = new DeliveryAddressDto();

        deliveryAddressDto.setId(deliveryAddress.getId());
        deliveryAddressDto.setStreet(deliveryAddress.getStreet());
        deliveryAddressDto.setHouseNumber(deliveryAddress.getHouseNumber());
        deliveryAddressDto.setTown(deliveryAddress.getTown());
        deliveryAddressDto.setPostalCode(deliveryAddress.getPostalCode());
        deliveryAddressDto.setCustomer(
                customerService.toDto(deliveryAddress.getCustomer())
        );

        return deliveryAddressDto;
    }

    @Override
    public DeliveryAddress fromDto(DeliveryAddressDto deliveryAddressDto) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();

        deliveryAddress.setId(deliveryAddressDto.getId());
        deliveryAddress.setStreet(deliveryAddressDto.getStreet());
        deliveryAddress.setHouseNumber(deliveryAddressDto.getHouseNumber());
        deliveryAddress.setTown(deliveryAddressDto.getTown());
        deliveryAddress.setPostalCode(deliveryAddressDto.getPostalCode());
        deliveryAddress.setCustomer(
                customerService.fromDto(deliveryAddressDto.getCustomer())
        );

        return deliveryAddress;
    }
}
