package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Customer;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.DeliveryAddress;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.Ordered;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.OrderedItem;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.domain.repository.OrderedRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.CustomerService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.DeliveryAddressService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.OrderedItemService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.OrderedService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto.Check;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderedServiceImpl implements OrderedService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedServiceImpl.class);

    private final OrderedRepository orderedRepository;
    private final CartService cartService;
    private final OrderedItemService orderedItemService;
    private final DeliveryAddressService deliveryAddressService;
    private final PizzaService pizzaService;
    private final CustomerService customerService;

    public OrderedServiceImpl(OrderedRepository orderedRepository, CartService cartService, OrderedItemService orderedItemService, DeliveryAddressService deliveryAddressService, PizzaService pizzaService, CustomerService customerService) {
        this.orderedRepository = orderedRepository;
        this.cartService = cartService;
        this.orderedItemService = orderedItemService;
        this.deliveryAddressService = deliveryAddressService;
        this.pizzaService = pizzaService;
        this.customerService = customerService;
    }

    @Override
    public void create(Ordered ordered) {
        orderedRepository.save(ordered);
    }

    @Override
    public Ordered addItemToOrdered(String userId, OrderedItem item) {
        Ordered ordered;
        try {
            ordered = orderedRepository.findByUserIdAndDeliveredNot(customerService.getCustomerNotDtoById(userId), true).get(0);
//        }catch (NullPointerException e){
        } catch (IndexOutOfBoundsException e) {
            ordered = new Ordered();
            ordered.setOrderedItems(new ArrayList<>());
            ordered.setUserId(customerService.getCustomerNotDtoById(userId));
            ordered.setDelivered(false);
            LOGGER.warn(e.getMessage() + " " + "User with id:(" + userId + ") have not non-delivered Orders");
        }
        List<OrderedItem> orderedItems = ordered.getOrderedItems();
        if (existItemInOrdered(orderedItems, item) == null) {
            OrderedItem orderedItem = orderedItemService.createOrderedItem(item);
            orderedItems.add(orderedItem);
        } else {
            OrderedItem orderedItem = existItemInOrdered(orderedItems, item);
            int index = orderedItems.indexOf(orderedItem);
            orderedItem = orderedItemService.updateItem(orderedItem, item);
            orderedItems.set(index, orderedItem);
        }
        ordered.setOrderedItems(orderedItems);
        return orderedRepository.save(ordered);
    }

    @Override
    public Ordered convertCartToOrdered(String userId) {
        try {
            Customer customer = customerService.getCustomerNotDtoById(userId);
            Cart cart = cartService.getCartByUserId(userId);
            for (Map.Entry<String, Item> entry : cart.getItems().entrySet()) {
                OrderedItem orderedItem = new OrderedItem();
                orderedItem.setPizza(entry.getValue().getPizza());
                orderedItem.setQuantity(entry.getValue().getQuantity());
                orderedItem.setSize(entry.getValue().getSize());
                orderedItem.setUserId(customer);
                addItemToOrdered(userId, orderedItem);
            }
            return orderedRepository.findByUserIdAndDeliveredNot(customerService.getCustomerNotDtoById(userId), true).get(0);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Ordered getOrderedByUserId(String userId) {
        return orderedRepository.findByUserIdAndDeliveredNot(customerService.getCustomerNotDtoById(userId), true).get(0);
    }

    @Override
    public BigDecimal getCommonPrice(Ordered ordered) {
        try{
            BigDecimal commonPrice = BigDecimal.ZERO;
            for (OrderedItem item : ordered.getOrderedItems()) {
                Pizza pizza = pizzaService.getPizzaById(item.getPizza().getId());
                switch (item.getSize()) {
                    case Small:
                        commonPrice = commonPrice.add(pizza.getPriceSmall().multiply(new BigDecimal(item.getQuantity())));
                        break;
                    case Large:
                        commonPrice = commonPrice.add(pizza.getPriceLarge().multiply(new BigDecimal(item.getQuantity())));
                        break;
                    case Medium:
                        commonPrice = commonPrice.add(pizza.getPriceMedium().multiply(new BigDecimal(item.getQuantity())));
                        break;
                }
            }
            return commonPrice;
        }catch (NullPointerException e){
            return new BigDecimal("0.00");
        }
    }

    @Override
    public Integer getCommonCount(Ordered ordered) {
        List<OrderedItem> items = ordered.getOrderedItems();
        int count = 0;
        for (OrderedItem item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    @Override
    public List<Check> getCheckOfItems(Ordered ordered) {
        List<Check> checkList = new ArrayList<>();
        for (OrderedItem orderedItem : ordered.getOrderedItems()) {
            Check check = new Check();
            check.setPizzaName(orderedItem.getPizza().getName());
            check.setPizzaSize(orderedItem.getSize().name());
            check.setQuantity(orderedItem.getQuantity());
            switch (orderedItem.getSize()) {
                case Small:
                    check.setPizzaPrice(orderedItem.getPizza().getPriceSmall());
                    break;
                case Medium:
                    check.setPizzaPrice(orderedItem.getPizza().getPriceMedium());
                    break;
                case Large:
                    check.setPizzaPrice(orderedItem.getPizza().getPriceLarge());
                    break;
            }
            checkList.add(check);
        }
        return checkList;
    }

    @Override
    public Ordered update(Ordered ordered) {
        return orderedRepository.save(ordered);
    }

    @Override
    public Ordered setDeliveryAddress(Ordered ordered, DeliveryAddress deliveryAddress) {
        return null;
    }

    @Override
    public Ordered increaseItemCount(String userId, OrderedItem item) {
        return null;
    }

    @Override
    public Ordered decreaseItemCount(String userId, OrderedItem item) {
        return null;
    }

    @Override
    public OrderedItem existItemInOrdered(List<OrderedItem> orderedItems, OrderedItem item) {
        for (OrderedItem value : orderedItems) {
            if (value.getPizza().equals(item.getPizza()) &&
                    value.getSize().equals(item.getSize())) {
                return value;
            }
        }
        return null;
    }

    @Override
    public Ordered delete(String userId) {
        return null;
    }

    @Override
    public Ordered deleteItemFromOrdered(String userId, OrderedItem item) {
        return null;
    }
}
