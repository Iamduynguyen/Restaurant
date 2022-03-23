package com.restarant.backend.service;

import com.restarant.backend.domain.Food;
import com.restarant.backend.service.dtoinput.FoodDtoInput;
import org.springframework.stereotype.Service;

@Service
public interface Foodservice {
    Food create(FoodDtoInput dtoInput);
}
