package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.Cart;
import com.kimtan.KT.Food.model.CartItem;
import com.kimtan.KT.Food.model.Food;
import com.kimtan.KT.Food.model.User;
import com.kimtan.KT.Food.repository.CartItemRepository;
import com.kimtan.KT.Food.repository.CartRepository;
import com.kimtan.KT.Food.repository.FoodRepository;
import com.kimtan.KT.Food.request.AddCartItemRequest;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);

        Food food = foodService.findFoodById(request.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getItems()){
            if (cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();

        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity()*food.getPrice());

        CartItem saveCartItem = cartItemRepository.save(newCartItem);

        cart.getItems().add(saveCartItem);

        return saveCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

        if (cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");

        }

        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);


        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

        if (cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");

        }
        CartItem item = cartItemOptional.get();

        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total = 0L;

        for (CartItem cartItem : cart.getItems()){
            total+= cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()){
            throw new Exception(" cart not found with id" + id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserid(Long userId) throws Exception {
  //      User user = userService.findByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
 //       User user = userService.findByJwtToken(jwt);
        Cart cart = findCartByUserid(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
