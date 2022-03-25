package com.restarant.backend.service;

import com.restarant.backend.domain.Food;
import com.restarant.backend.service.dtoinput.FoodDtoInput;
import com.restarant.backend.service.dtooutput.FoodOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public interface Foodservice {
    Food create(FoodDtoInput dtoInput);
    Page<FoodOutDto> getAll(Pageable pageable);
}
