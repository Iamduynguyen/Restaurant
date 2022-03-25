package com.restarant.backend.web.restapi;

import com.restarant.backend.domain.FoodMedia;
import com.restarant.backend.repository.FoodMediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class FoodMediaResource {

    private final FoodMediaRepository foodMediaRepository;

    public FoodMediaResource(FoodMediaRepository foodMediaRepository) {
        this.foodMediaRepository = foodMediaRepository;
    }


    @GetMapping("/food-medias/{fooddetailId}")
    public ResponseEntity<?> getByFoodId(@PathVariable Long fooddetailId) {
        return ResponseEntity.ok(foodMediaRepository.getByfoodId(fooddetailId));
    }

    @DeleteMapping("/food-medias/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(foodMediaRepository.getByfoodId(id));
    }

}
