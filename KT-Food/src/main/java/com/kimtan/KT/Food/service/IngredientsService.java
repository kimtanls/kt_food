package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.IngredientsCategory;
import com.kimtan.KT.Food.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientsCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientsCategory findIngredientCategoryId(Long id) throws Exception;

    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);

    public IngredientsItem updateStock(Long id) throws Exception;
}
