package com.restarant.backend.web.restapi;

import com.restarant.backend.domain.Food;
import com.restarant.backend.repository.FoodRepository;
import com.restarant.backend.service.Foodservice;
import com.restarant.backend.service.dtoinput.FoodDtoInput;
import com.restarant.backend.service.dtooutput.FoodOutDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Transactional
@CrossOrigin("*")
public class FoodResource {

    private final Logger log = LoggerFactory.getLogger(FoodResource.class);

    private static final String ENTITY_NAME = "food";


    private final FoodRepository foodRepository;
    private final Foodservice foodservice;

    public FoodResource(FoodRepository foodRepository, Foodservice foodservice) {
        this.foodRepository = foodRepository;
        this.foodservice = foodservice;
    }

    /**
     * {@code POST  /foods} : Create a new food.
     *
     * @param foodDtoInput the food to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new food, or with status {@code 400 (Bad Request)} if the food has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/foods")
    public ResponseEntity<Food> createFood(@RequestBody FoodDtoInput foodDtoInput) throws URISyntaxException {
        log.debug("REST request to save Food : {}", foodDtoInput);
        Food result = foodservice.create(foodDtoInput);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /foods/:id} : Updates an existing food.
     *
     * @param id the id of the food to save.
     * @param food the food to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated food,
     * or with status {@code 400 (Bad Request)} if the food is not valid,
     * or with status {@code 500 (Internal Server Error)} if the food couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/foods/{id}")
    public ResponseEntity<?> updateFood(@PathVariable(value = "id", required = false) final Long id, @RequestBody Food food)
        throws URISyntaxException {
        log.debug("REST request to update Food : {}, {}", id, food);

        if (!foodRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("entity not found");
        }

        Food result = foodRepository.save(food);
        return ResponseEntity.ok().body(result);
    }


    /**
     * {@code GET  /foods} : get all the foods.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foods in body.
     */
    @GetMapping("/foods")
    public ResponseEntity<?> getAllFoods(Pageable pageable) {
        log.debug("REST request to get all Foods");
        return ResponseEntity.ok(foodservice.getAll(pageable).getContent());
    }

    /**
     * {@code GET  /foods/:id} : get the "id" food.
     *
     * @param id the id of the food to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the food, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/foods/{id}")
    public ResponseEntity<Food> getFood(@PathVariable Long id) {
        log.debug("REST request to get Food : {}", id);
        Optional<Food> food = foodRepository.findById(id);
        return ResponseEntity.ok(food.get());
    }

    /**
     * {@code DELETE  /foods/:id} : delete the "id" food.
     *
     * @param id the id of the food to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/foods/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        log.debug("REST request to delete Food : {}", id);
        foodRepository.deleteById(id);
        return ResponseEntity
            .noContent().build();
    }
}
