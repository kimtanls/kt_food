package com.kimtan.KT.Food.request;

import com.kimtan.KT.Food.model.Category;
import com.kimtan.KT.Food.model.IngredientsItem;
import lombok.Data;
import org.w3c.dom.ls.LSException;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarin;
    private boolean seasional;
    private List<IngredientsItem> ingredients;
}
