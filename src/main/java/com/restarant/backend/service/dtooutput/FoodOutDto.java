package com.restarant.backend.service.dtooutput;

import com.restarant.backend.domain.FoodDetalls;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FoodOutDto {
    private Long id;
    private String title;
    private LocalDate create;
    private String name;
    private Long views;
    private Long notes;
    private List<FoodDetalls> foodDetalls;
}
