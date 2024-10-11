package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.*;
import com.kimtan.KT.Food.repository.*;
import com.kimtan.KT.Food.request.OrderRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {

        Address shipAddress = order.getDeliveryAddress();
        Address saveAddress = addressRepository.save(shipAddress);

        if (!user.getAddresses().contains(saveAddress)){
            user.getAddresses().add(saveAddress);
            userRepository.save(user);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreateAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setDeliceryAddress(saveAddress);
        createOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserid(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            OrderItem saveOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(saveOrderItem);
        }
        Long totalPrice = cartService.calculateCartTotals(cart);

        createOrder.setItems(orderItems);
        createOrder.setTotalPrice(totalPrice);

        Order saveOrder = orderRepository.save(createOrder);
        restaurant.getOrders().add(saveOrder);

        return createOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);

        if (orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders =orderRepository.findByRestaurantId(restaurantId);

        if (orderStatus!= null){
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()){
            throw new Exception("order not found");
        }

        return optionalOrder.get();
    }
}
