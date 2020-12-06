package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Cart;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Item;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.Pizza;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.domain.repository.CartRepository;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.CartService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.ItemService;
import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.warencorb.service.PizzaService;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;
    private final ItemService itemService;
    private final PizzaService pizzaService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ItemService itemService, PizzaService pizzaService) {
        this.cartRepository = cartRepository;
        this.itemService = itemService;
        this.pizzaService = pizzaService;
    }

    @Override
    public void create(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart addItemToCart(String userId, Item item) {
        Cart thisCart = cartRepository.getCartByUserId(userId);
        Map<String,Item> items;
        try {
             items= thisCart.getItems();
        }catch (NullPointerException e){
            items = new HashMap<>();
        }
        if (existItemInCart(userId,item) != null){
            Item increaseItem = items.get(existItemInCart(userId,item).getId());
            increaseItem.setQuantity(increaseItem.getQuantity()+item.getQuantity());
            items.put(increaseItem.getId(),increaseItem);
            itemService.updateItem(increaseItem);
        }else {
            item = itemService.createItem(item);
            items.put(item.getId(),item);
        }
        thisCart.setItems(items);
        cartRepository.save(thisCart);
        return thisCart;
    }

    @Override
    public Cart getCartByUserId(String userId) {
        try {
            return cartRepository.getCartByUserId(userId);
        }catch (NullPointerException e){
            LOGGER.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public BigDecimal getCommonPrice(String userId) {
        List<Item> items = getListOfItems(userId);
        try{
            BigDecimal commonPrice = BigDecimal.ZERO;
            for (Item item : items) {
                Pizza pizza = pizzaService.getPizzaById(item.getPizza().getId());
                switch (item.getSize()) {
                    case Small:
                        commonPrice = commonPrice.add(pizza.getPriceSmall().multiply(new BigDecimal(item.getQuantity())));
                        LOGGER.info("Warencorb: Small Pizza: " + pizza.getName()
                                + " Price " + pizza.getPriceSmall()
                                + " Count " + item.getQuantity()
                                + " Common price " + commonPrice);
                        break;
                    case Large:
                        commonPrice = commonPrice.add(pizza.getPriceLarge().multiply(new BigDecimal(item.getQuantity())));
                        LOGGER.info("Warencorb: Large Pizza: " + pizza.getName()
                                + " Price " + pizza.getPriceLarge()
                                + " Count " + item.getQuantity()
                                + " Common price " + commonPrice);
                        break;
                    case Medium:
                        commonPrice = commonPrice.add(pizza.getPriceMedium().multiply(new BigDecimal(item.getQuantity())));
                        LOGGER.info("Warencorb: Medium Pizza: " + pizza.getName()
                                + " Price " + pizza.getPriceMedium()
                                + " Count " + item.getQuantity()
                                + " Common price " + commonPrice);
                        break;
                }
            }
            return commonPrice;
        }catch (NullPointerException e){
            return new BigDecimal("0.00");
        }
    }

    @Override
    public Integer getCommonCount(String userId) {
        List<Item> items = getListOfItems(userId);
        int count = 0;
        for (Item item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    @Override
    public ArrayList<Item> getListOfItems(String userId) {
        try {
            Cart thisCart = cartRepository.getCartByUserId(userId);
            return new ArrayList<>(thisCart.getItems().values());
        }catch (NullPointerException e){
            return new ArrayList<>();
        }

    }

    @Override
    public Item existItemInCart(String userId, Item item) {
        try {
            List<Item> items = getListOfItems(userId);
            for (Item value : items) {
                if (value.getPizza().equals(item.getPizza()) &&
                        value.getSize().equals(item.getSize())) {
                    return value;
                }
            }
            return null;
        }catch (NullPointerException e){
            LOGGER.error("Dies Customer hat kein WarenCorb");
            return null;
        }
    }

    @Override
    public Cart update(Cart cart) {
        if (cartRepository.existsByUserId(cart.getUser().getId())){
            return cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Cart increaseItemCount(String userId, Item item) {
        try {
            Cart thisCart = cartRepository.getCartByUserId(userId);
            Map<String,Item> itemMap = thisCart.getItems();
            Item increaseItem = itemMap.get(item.getId());
            increaseItem.setQuantity(increaseItem.getQuantity()+1);
            itemMap.put(increaseItem.getId(),increaseItem);
            thisCart.setItems(itemMap);
            itemService.updateItem(increaseItem);
            return cartRepository.save(thisCart);
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public Cart decreaseItemCount(String userId, Item item) {
        try {
            Cart thisCart = cartRepository.getCartByUserId(userId);
            Map<String,Item> itemMap = thisCart.getItems();
            Item increaseItem = itemMap.get(item.getId());
            increaseItem.setQuantity(increaseItem.getQuantity()-1);
            itemMap.put(increaseItem.getId(),increaseItem);
            thisCart.setItems(itemMap);
            itemService.updateItem(increaseItem);
            return cartRepository.save(thisCart);
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public Cart delete(String userId) {
        if (cartRepository.existsByUserId(userId)){
            Cart cart = cartRepository.getCartByUserId(userId);
            cartRepository.delete(cart);
            return cart;
        }
        return null;
    }

    @Override
    public Cart deleteItemFromCart(String userId, Item item) {
        try {
            Cart thisCart = cartRepository.getCartByUserId(userId);
//            List<Item> items = getListOfItems(userId);
//            items.remove(item);items.stream().collect(Collectors.toMap(Item::getId,Item -> item));
            Map<String,Item> itemMap = thisCart.getItems();
            itemMap.remove(item.getId());
            thisCart.setItems(itemMap);
            return cartRepository.save(thisCart);
        }catch (NullPointerException e){
            return null;
        }
    }
}
