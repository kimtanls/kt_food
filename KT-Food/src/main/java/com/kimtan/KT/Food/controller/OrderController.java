package com.kimtan.KT.Food.controller;


import com.kimtan.KT.Food.model.CartItem;
import com.kimtan.KT.Food.model.Order;
import com.kimtan.KT.Food.model.User;
import com.kimtan.KT.Food.request.AddCartItemRequest;
import com.kimtan.KT.Food.request.OrderRequest;
import com.kimtan.KT.Food.service.OrderService;
import com.kimtan.KT.Food.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request,
                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findByJwtToken(jwt);
        Order order = orderService.createOrder(request, user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order> > getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findByJwtToken(jwt);
        List<Order> order = orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
