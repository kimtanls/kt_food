package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.Category;
import com.kimtan.KT.Food.model.Food;
import com.kimtan.KT.Food.model.Restaurant;
import com.kimtan.KT.Food.repository.FoodRepository;
import com.kimtan.KT.Food.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService{
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) {
       Food food = new Food();
       food.setFoodCategory(category);
       food.setRestaurant(restaurant);
       food.setDescription(request.getDescription());
       food.setImages(request.getImages());
       food.setName(request.getName());
       food.setPrice(food.getPrice());
       food.setIngredients(request.getIngredients());
        food.setSeasonal(request.isSeasional());
        food.setVegetarian(request.isVegetarin());
        Food saveFood = foodRepository.save(food);

        restaurant.getFoods().add(saveFood);

        return saveFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food  food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if (isVegetarian){
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if (isNonveg){
            foods = filterByNonveg(foods,isNonveg);
        }
        if (isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if (foodCategory != null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {

        return foods.stream().filter(food ->{
            if (food.getFoodCategory() != null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());

    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {

        return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());

    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());

    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);

        if (optionalFood.isEmpty()){
            throw new Exception("food not exit..");
        }

        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
