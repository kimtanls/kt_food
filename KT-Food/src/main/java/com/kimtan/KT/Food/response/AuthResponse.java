package com.kimtan.KT.Food.response;

import com.kimtan.KT.Food.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
