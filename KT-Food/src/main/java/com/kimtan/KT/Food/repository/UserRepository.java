package com.kimtan.KT.Food.repository;

import com.kimtan.KT.Food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
