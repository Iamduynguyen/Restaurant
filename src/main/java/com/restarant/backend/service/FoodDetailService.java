package com.restarant.backend.service;

import com.restarant.backend.domain.FoodDetalls;
import com.restarant.backend.service.dtoinput.FoodDetailDtoInput;
import org.springframework.stereotype.Service;

@Service
public interface FoodDetailService {
    FoodDetalls create(FoodDetailDtoInput input);
}
