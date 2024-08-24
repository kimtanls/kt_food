package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.IngredientsCategory;
import com.kimtan.KT.Food.model.IngredientsItem;
import com.kimtan.KT.Food.model.Restaurant;
import com.kimtan.KT.Food.repository.IngredientCategoryRepository;
import com.kimtan.KT.Food.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientsService{
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientsCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientsCategory category = new IngredientsCategory();

        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);

    }

    @Override
    public IngredientsCategory findIngredientCategoryId(Long id) throws Exception {

        Optional<IngredientsCategory> opt = ingredientCategoryRepository.findById(id);

        if ((opt.isEmpty())){
            throw new Exception("ingredient category not found");
        }

        return opt.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {

        restaurantService.findRestaurantById(id);

        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory category = findIngredientCategoryId(categoryId);

        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem ingredient = ingredientItemRepository.save(item);
        category.getIngredientsItems().add(ingredient);
        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {

        Optional<IngredientsItem> optionalIngredientsItem = ingredientItemRepository.findById(id);

        if (optionalIngredientsItem.isEmpty()){
            throw new Exception("ingredient not found");
        }

        IngredientsItem ingredientsItem = optionalIngredientsItem.get();
        ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
        return ingredientItemRepository.save(ingredientsItem);
    }
}
