package com.restarant.backend.web.restapi;

import com.restarant.backend.domain.Tables;
import com.restarant.backend.repository.TablesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@RestController
@RequestMapping("/api")
@Transactional
public class TablesResource {

    private final Logger log = LoggerFactory.getLogger(TablesResource.class);

    private static final String ENTITY_NAME = "tables";


    private final TablesRepository tablesRepository;

    public TablesResource(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    /**
     * {@code POST  /tables} : Create a new tables.
     *
     * @param tables the tables to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tables, or with status {@code 400 (Bad Request)} if the tables has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tables")
    public ResponseEntity<Tables> createTables(@RequestBody Tables tables) throws URISyntaxException {
        log.debug("REST request to save Tables : {}", tables);
        Tables result = tablesRepository.save(tables);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /tables/:id} : Updates an existing tables.
     *
     * @param id the id of the tables to save.
     * @param tables the tables to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tables,
     * or with status {@code 400 (Bad Request)} if the tables is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tables couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tables/{id}")
    public ResponseEntity<?> updateTables(@PathVariable(value = "id", required = false) final Long id, @RequestBody Tables tables)
        throws URISyntaxException {
        log.debug("REST request to update Tables : {}, {}", id, tables);

        if (!tablesRepository.existsById(id)) {
            return  ResponseEntity.badRequest().body("entity not found");
        }

        Tables result = tablesRepository.save(tables);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /tables} : get all the tables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tables in body.
     */
    @GetMapping("/tables")
    public List<Tables> getAllTables() {
        log.debug("REST request to get all Tables");
        return tablesRepository.findAll();
    }

    /**
     * {@code GET  /tables/:id} : get the "id" tables.
     *
     * @param id the id of the tables to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tables, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tables/{id}")
    public ResponseEntity<Tables> getTables(@PathVariable Long id) {
        log.debug("REST request to get Tables : {}", id);
        Optional<Tables> tables = tablesRepository.findById(id);
        return ResponseEntity.of(tables);
    }

    /**
     * {@code DELETE  /tables/:id} : delete the "id" tables.
     *
     * @param id the id of the tables to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tables/{id}")
    public ResponseEntity<Void> deleteTables(@PathVariable Long id) {
        log.debug("REST request to delete Tables : {}", id);
        tablesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
