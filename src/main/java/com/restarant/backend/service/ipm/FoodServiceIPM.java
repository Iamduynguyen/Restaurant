package com.restarant.backend.service.ipm;

import com.restarant.backend.domain.Food;
import com.restarant.backend.repository.CategoryRepository;
import com.restarant.backend.repository.FoodRepository;
import com.restarant.backend.service.Foodservice;
import com.restarant.backend.service.dtoinput.FoodDtoInput;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FoodServiceIPM implements Foodservice {
    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;

    public FoodServiceIPM(FoodRepository foodRepository, CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Food create(FoodDtoInput dtoInput) {
        Food entity = convertToEntity(dtoInput);
        entity.setCreate(LocalDate.now());
        entity.setDeleteflag(1l);
        entity.setViews(0l);
        return foodRepository.save(entity);
    }

    Food convertToEntity(FoodDtoInput dtoInput){
        Food entity = new Food();
        entity.setCategory(categoryRepository.findById(dtoInput.getCategory()).get());
        entity.setName(dtoInput.getName());
        entity.setNotes(dtoInput.getNotes());
        entity.setTitle(dtoInput.getTitle());
        return entity;
    }
}
