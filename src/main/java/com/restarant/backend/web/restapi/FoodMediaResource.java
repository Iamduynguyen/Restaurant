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
@Transactional
public class FoodMediaResource {

    private final Logger log = LoggerFactory.getLogger(FoodMediaResource.class);

    private static final String ENTITY_NAME = "foodMedia";



    private final FoodMediaRepository foodMediaRepository;

    public FoodMediaResource(FoodMediaRepository foodMediaRepository) {
        this.foodMediaRepository = foodMediaRepository;
    }

    /**
     * {@code POST  /food-medias} : Create a new foodMedia.
     *
     * @param foodMedia the foodMedia to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodMedia, or with status {@code 400 (Bad Request)} if the foodMedia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-medias")
    public ResponseEntity<FoodMedia> createFoodMedia(@RequestBody FoodMedia foodMedia) throws URISyntaxException {
        log.debug("REST request to save FoodMedia : {}", foodMedia);
        FoodMedia result = foodMediaRepository.save(foodMedia);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /food-medias/:id} : Updates an existing foodMedia.
     *
     * @param id the id of the foodMedia to save.
     * @param foodMedia the foodMedia to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodMedia,
     * or with status {@code 400 (Bad Request)} if the foodMedia is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodMedia couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-medias/{id}")
    public ResponseEntity<?> updateFoodMedia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodMedia foodMedia
    ) throws URISyntaxException {
        log.debug("REST request to update FoodMedia : {}, {}", id, foodMedia);
        if (!foodMediaRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("entity not exit");
        }

        FoodMedia result = foodMediaRepository.save(foodMedia);
        return ResponseEntity.ok().body(result);
    }



    /**
     * {@code GET  /food-medias} : get all the foodMedias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodMedias in body.
     */
    @GetMapping("/food-medias")
    public List<FoodMedia> getAllFoodMedias() {
        log.debug("REST request to get all FoodMedias");
        return foodMediaRepository.findAll();
    }

    /**
     * {@code GET  /food-medias/:id} : get the "id" foodMedia.
     *
     * @param id the id of the foodMedia to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodMedia, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-medias/{id}")
    public ResponseEntity<FoodMedia> getFoodMedia(@PathVariable Long id) {
        log.debug("REST request to get FoodMedia : {}", id);
        Optional<FoodMedia> foodMedia = foodMediaRepository.findById(id);
        return ResponseEntity.ok(foodMedia.get());
    }

    /**
     * {@code DELETE  /food-medias/:id} : delete the "id" foodMedia.
     *
     * @param id the id of the foodMedia to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-medias/{id}")
    public ResponseEntity<Void> deleteFoodMedia(@PathVariable Long id) {
        log.debug("REST request to delete FoodMedia : {}", id);
        foodMediaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
