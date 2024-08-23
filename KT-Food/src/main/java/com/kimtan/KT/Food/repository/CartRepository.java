package com.kimtan.KT.Food.repository;

import com.kimtan.KT.Food.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartRepository extends JpaRepository<Cart,Long> {
}
