package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.dto.RestaurantDto;
import com.kimtan.KT.Food.model.Restaurant;
import com.kimtan.KT.Food.model.User;
import com.kimtan.KT.Food.request.CreateRestaurantRequest;
import lombok.extern.java.Log;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(
            CreateRestaurantRequest request, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

   public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
