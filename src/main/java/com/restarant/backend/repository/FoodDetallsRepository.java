package com.restarant.backend.repository;


import com.restarant.backend.domain.FoodDetalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FoodDetalls entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodDetallsRepository extends JpaRepository<FoodDetalls, Long> {}
