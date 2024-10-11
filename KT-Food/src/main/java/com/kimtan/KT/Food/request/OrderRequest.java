package com.kimtan.KT.Food.request;

import com.kimtan.KT.Food.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;
}
