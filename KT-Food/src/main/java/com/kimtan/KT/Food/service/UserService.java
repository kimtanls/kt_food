package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.model.User;

public interface UserService {

    public User findByJwtToken(String jwt) throws Exception;
    public User FindUserByEmail( String email) throws Exception;

}
