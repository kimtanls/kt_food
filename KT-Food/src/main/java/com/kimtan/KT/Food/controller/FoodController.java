package com.kimtan.KT.Food.controller;

import com.kimtan.KT.Food.model.Food;
import com.kimtan.KT.Food.model.Restaurant;
import com.kimtan.KT.Food.model.User;
import com.kimtan.KT.Food.request.CreateFoodRequest;
import com.kimtan.KT.Food.service.FoodService;
import com.kimtan.KT.Food.service.RestaurantService;
import com.kimtan.KT.Food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);

        List<Food> food = foodService.searchFood(name);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @RequestParam (required = false) String food_category,
             @PathVariable Long restaurantId,
             @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);

        List<Food> food = foodService.getRestaurantFood(restaurantId,vegetarian,nonveg,seasonal,food_category);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
