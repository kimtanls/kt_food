package com.kimtan.KT.Food.request;

import com.kimtan.KT.Food.model.Address;
import com.kimtan.KT.Food.model.ContactInformation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
    private LocalDateTime register;
}
