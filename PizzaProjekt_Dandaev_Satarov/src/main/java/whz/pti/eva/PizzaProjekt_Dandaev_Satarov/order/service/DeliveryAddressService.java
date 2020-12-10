package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.DeliveryAddress;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.CustomerDto;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.DeliveryAddressDto;

import java.util.List;

public interface DeliveryAddressService {
    DeliveryAddressDto getDeliveryAddressById(String id);
    List<DeliveryAddressDto> getAllDeliveryAddresses();
    List<DeliveryAddressDto> getDeliveryAddressesByCustomer(CustomerDto customerDto);
    boolean existsById(String id);

    DeliveryAddress create(DeliveryAddressDto deliveryAddressDto);
    DeliveryAddress update(DeliveryAddressDto deliveryAddressDto);
    void delete(DeliveryAddressDto deliveryAddressDto);

    DeliveryAddressDto toDto(DeliveryAddress deliveryAddress);
    DeliveryAddress fromDto(DeliveryAddressDto deliveryAddressDto);
}
