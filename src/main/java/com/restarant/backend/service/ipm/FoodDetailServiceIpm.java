package com.restarant.backend.service.ipm;

import com.restarant.backend.domain.FoodDetalls;
import com.restarant.backend.domain.FoodMedia;
import com.restarant.backend.repository.FoodDetallsRepository;
import com.restarant.backend.repository.FoodMediaRepository;
import com.restarant.backend.repository.FoodRepository;
import com.restarant.backend.service.FoodDetailService;
import com.restarant.backend.service.dtoinput.FoodDetailDtoInput;

public class FoodDetailServiceIpm implements FoodDetailService {
    private final FoodRepository foodRepository;
    private final FoodDetallsRepository foodDetallsRepository;
    private final FoodMediaRepository mediaRepository;

    public FoodDetailServiceIpm(FoodRepository foodRepository, FoodDetallsRepository foodDetallsRepository, FoodMediaRepository mediaRepository) {
        this.foodRepository = foodRepository;
        this.foodDetallsRepository = foodDetallsRepository;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public FoodDetalls create(FoodDetailDtoInput input) {
        FoodDetalls entity = convertToEntity(input);
        entity.setDeleteflag(1l);
        FoodDetalls rs = foodDetallsRepository.save(entity);
        if (input.getFoodMedias()!=null){
            for (FoodMedia media : input.getFoodMedias()){
                String x = media.getFoodurl();
                media.setDeleteflag(1l);
                if (x.contains("jpeg")||x.contains("jpg")||x.contains("png")){
                    media.setFoodtype("image");
                }
                if (x.contains("mp4")||x.contains("avi")||x.contains("video")){
                    media.setFoodtype("video");
                }
                media.foodDetalls(rs);
                mediaRepository.save(media);
            }
        }
        return null;
    }

    FoodDetalls convertToEntity(FoodDetailDtoInput input){
        FoodDetalls foodDetalls = new FoodDetalls();
        foodDetalls.setFood(foodRepository.findById(input.getFoodid()).get());
        foodDetalls.setFoodsize(input.getFoodsize());
        foodDetalls.setAmount(input.getAmount());
        foodDetalls.setDiscount(input.getDiscount());
        return foodDetalls;
    }
}
