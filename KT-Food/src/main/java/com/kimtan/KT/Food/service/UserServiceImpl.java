package com.kimtan.KT.Food.service;

import com.kimtan.KT.Food.config.JwtProvider;
import com.kimtan.KT.Food.model.User;
import com.kimtan.KT.Food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User FindUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if (user==null){
            throw new Exception("User not found");
        }
        return user;
    }
}
