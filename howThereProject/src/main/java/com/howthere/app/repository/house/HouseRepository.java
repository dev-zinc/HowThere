package com.howthere.app.repository.house;

import com.howthere.app.entity.house.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
