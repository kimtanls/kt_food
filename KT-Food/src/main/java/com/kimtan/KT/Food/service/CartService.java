package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.Cart;
import com.kimtan.KT.Food.model.CartItem;
import com.kimtan.KT.Food.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest request, String jwt)throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

     public Cart findCartById(Long id) throws Exception;

     public Cart findCartByUserid(String jwt) throws Exception;

     public Cart clearCart(String jwt) throws Exception;
}
