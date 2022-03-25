package com.restarant.backend.repository;


import com.restarant.backend.domain.FoodDetalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the FoodDetalls entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodDetallsRepository extends JpaRepository<FoodDetalls, Long> {
    @Query(value = "select f from FoodDetalls f where f.food.id = :id")
    List<FoodDetalls> getByFoodId(@Param("id") Long id);

}
