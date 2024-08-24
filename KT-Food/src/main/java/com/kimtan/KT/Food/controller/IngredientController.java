package com.kimtan.KT.Food.controller;

import com.kimtan.KT.Food.model.IngredientsCategory;
import com.kimtan.KT.Food.model.IngredientsItem;
import com.kimtan.KT.Food.request.IngredientCategoryRequest;
import com.kimtan.KT.Food.request.IngredientRequest;
import com.kimtan.KT.Food.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest request) throws Exception {

        IngredientsCategory item = ingredientsService.createIngredientCategory(request.getName(), request.getRestaurantId());

        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }

    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest request) throws Exception {

        IngredientsItem item = ingredientsService.createIngredientItem(request.getRestaurantId(),request.getName(),request.getCategoryId());

        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStoke(@PathVariable Long id) throws Exception {

        IngredientsItem item = ingredientsService.updateStock(id);

        return new ResponseEntity<>(item, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {

        List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);

        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {

        List<IngredientsCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(items, HttpStatus.OK);

    }
}
