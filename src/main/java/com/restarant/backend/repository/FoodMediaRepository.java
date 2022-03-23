package com.restarant.backend.repository;

import com.restarant.backend.domain.FoodMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FoodMedia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodMediaRepository extends JpaRepository<FoodMedia, Long> {}
