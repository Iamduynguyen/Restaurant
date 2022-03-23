package com.restarant.backend.service.dtoinput;

import com.restarant.backend.domain.FoodMedia;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FoodDetailDtoInput {
    private Long id;
    private String foodsize;
    private BigDecimal discount;
    private BigDecimal amount;
    private Long foodid;
    private List<FoodMedia> foodMedias;

}
