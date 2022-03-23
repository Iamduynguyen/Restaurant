package com.restarant.backend.service.dtoinput;

import lombok.Data;


@Data
public class FoodDtoInput {
    private Long id;
    private String title;
    private String name;
    private Long notes;
    private Long category;
}
