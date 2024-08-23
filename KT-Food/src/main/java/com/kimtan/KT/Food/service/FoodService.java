package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.Category;
import com.kimtan.KT.Food.model.Food;
import com.kimtan.KT.Food.model.Restaurant;
import com.kimtan.KT.Food.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest request,
                           Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian,
                                        boolean isNonveg, boolean isSeasonal, String foodCategory);


    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;


}
