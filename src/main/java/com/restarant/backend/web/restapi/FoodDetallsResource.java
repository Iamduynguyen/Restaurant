package com.restarant.backend.web.restapi;

import com.restarant.backend.domain.FoodDetalls;
import com.restarant.backend.repository.FoodDetallsRepository;
import com.restarant.backend.service.dtoinput.FoodDetailDtoInput;
import com.restarant.backend.service.ipm.FoodDetailServiceIpm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Transactional
@CrossOrigin("*")
public class FoodDetallsResource {

    private final Logger log = LoggerFactory.getLogger(FoodDetallsResource.class);

    private static final String ENTITY_NAME = "foodDetalls";
    private final FoodDetallsRepository foodDetallsRepository;

    @Autowired
    FoodDetailServiceIpm foodservice;

    public FoodDetallsResource(FoodDetallsRepository foodDetallsRepository) {
        this.foodDetallsRepository = foodDetallsRepository;
    }

    /**
     * {@code POST  /food-detalls} : Create a new foodDetalls.
     *
     * @param foodDetalls the foodDetalls to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodDetalls, or with status {@code 400 (Bad Request)} if the foodDetalls has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-detalls")
    public ResponseEntity<FoodDetalls> createFoodDetalls(@RequestBody FoodDetailDtoInput foodDetalls) throws URISyntaxException {
        System.out.println(foodDetalls.toString());
        FoodDetalls result = foodservice.create(foodDetalls);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /food-detalls/:id} : Updates an existing foodDetalls.
     *
     * @param id the id of the foodDetalls to save.
     * @param foodDetalls the foodDetalls to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodDetalls,
     * or with status {@code 400 (Bad Request)} if the foodDetalls is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodDetalls couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-detalls/{id}")
    public ResponseEntity<FoodDetalls> updateFoodDetalls(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodDetalls foodDetalls
    ) throws URISyntaxException {
        log.debug("REST request to update FoodDetalls : {}, {}", id, foodDetalls);
        FoodDetalls result = foodDetallsRepository.save(foodDetalls);
        return ResponseEntity.ok().body(result);
    }


    /**
     * {@code GET  /food-detalls} : get all the foodDetalls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodDetalls in body.
     */
    @GetMapping("/food-detalls")
    public List<FoodDetalls> getAllFoodDetalls() {
        log.debug("REST request to get all FoodDetalls");
        return foodDetallsRepository.findAll();
    }

    /**
     * {@code GET  /food-detalls/:id} : get the "id" foodDetalls.
     *
     * @param id the id of the foodDetalls to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodDetalls, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-detalls/{id}")
    public ResponseEntity<FoodDetalls> getFoodDetalls(@PathVariable Long id) {
        log.debug("REST request to get FoodDetalls : {}", id);
        Optional<FoodDetalls> foodDetalls = foodDetallsRepository.findById(id);
        return ResponseEntity.ok(foodDetalls.get());
    }

    /**
     * {@code DELETE  /food-detalls/:id} : delete the "id" foodDetalls.
     *
     * @param id the id of the foodDetalls to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-detalls/{id}")
    public ResponseEntity<Void> deleteFoodDetalls(@PathVariable Long id) {
        log.debug("REST request to delete FoodDetalls : {}", id);
        foodDetallsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
