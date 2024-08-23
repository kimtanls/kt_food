package com.kimtan.KT.Food.controller;

import com.kimtan.KT.Food.model.Food;
import com.kimtan.KT.Food.model.Restaurant;
import com.kimtan.KT.Food.model.User;
import com.kimtan.KT.Food.request.CreateFoodRequest;

import com.kimtan.KT.Food.response.MessageResponse;
import com.kimtan.KT.Food.service.FoodService;
import com.kimtan.KT.Food.service.FoodServiceImp;
import com.kimtan.KT.Food.service.RestaurantService;
import com.kimtan.KT.Food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService  restaurantService;

    @PostMapping()
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        Food food = foodService.createFood(request,request.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> createFood(@RequestHeader("Authorization") String jwt,
                                                      @PathVariable Long id
                                           ) throws Exception {

        User user = userService.findByJwtToken(jwt);

       foodService.deleteFood(id);

        MessageResponse response = new MessageResponse();
        response.setMassage("food deleted successfully");

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailability(@RequestHeader("Authorization") String jwt,
                                                      @PathVariable Long id
    ) throws Exception {

        User user = userService.findByJwtToken(jwt);

        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food,HttpStatus.OK);
    }
}
