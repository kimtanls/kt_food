package com.kimtan.KT.Food.repository;

import com.kimtan.KT.Food.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
