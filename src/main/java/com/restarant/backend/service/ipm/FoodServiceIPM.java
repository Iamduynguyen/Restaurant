package com.restarant.backend.service.ipm;

import com.restarant.backend.domain.Food;
import com.restarant.backend.domain.FoodDetalls;
import com.restarant.backend.repository.CategoryRepository;
import com.restarant.backend.repository.FoodDetallsRepository;
import com.restarant.backend.repository.FoodMediaRepository;
import com.restarant.backend.repository.FoodRepository;
import com.restarant.backend.service.Foodservice;
import com.restarant.backend.service.dtoinput.FoodDtoInput;
import com.restarant.backend.service.dtooutput.FoodOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FoodServiceIPM implements Foodservice {
    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;
    private final FoodDetallsRepository foodDetallsRepository;
    private final FoodMediaRepository mediaRepository;

    public FoodServiceIPM(FoodRepository foodRepository, CategoryRepository categoryRepository, FoodDetallsRepository foodDetallsRepository, FoodMediaRepository mediaRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
        this.foodDetallsRepository = foodDetallsRepository;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public Food create(FoodDtoInput dtoInput) {
        Food entity = convertToEntity(dtoInput);
        entity.setCreate(LocalDate.now());
        entity.setDeleteflag(1l);
        entity.setViews(0l);
        return foodRepository.save(entity);
    }

    @Override
    public Page<FoodOutDto> getAll(Pageable pageable) {
        Page<Food> all = foodRepository.findAll(pageable);
        List<FoodOutDto> rs = all.getContent().stream().map(f ->convertToDto(f)).collect(Collectors.toList());
        return new PageImpl<>(rs,pageable,all.getSize());
    }

    Food convertToEntity(FoodDtoInput dtoInput){
        Food entity = new Food();
        entity.setCategory(categoryRepository.findById(dtoInput.getCategory()).get());
        entity.setName(dtoInput.getName());
        entity.setNotes(dtoInput.getNotes());
        entity.setTitle(dtoInput.getTitle());
        return entity;
    }

    FoodOutDto convertToDto(Food entity){
        FoodOutDto dto = new FoodOutDto();
        dto.setId(entity.getId());
        dto.setCreate(entity.getCreate());
        dto.setName(entity.getName());
        dto.setViews(entity.getViews());
        dto.setNotes(entity.getNotes());
        dto.setTitle(entity.getTitle());
        List<FoodDetalls> rs = foodDetallsRepository.getByFoodId(dto.getId());
        for (FoodDetalls x:rs){
            x.setFoodMedias(mediaRepository.getByfoodId(x.getId()));
        }
        dto.setFoodDetalls(rs);
        return dto;
    }
}
