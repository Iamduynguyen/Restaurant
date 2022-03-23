package com.restarant.backend.web.restapi;

import com.restarant.backend.domain.TableOrder;
import com.restarant.backend.repository.TableOrderRepository;
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
public class TableOrderResource {

    private final Logger log = LoggerFactory.getLogger(TableOrderResource.class);

    private static final String ENTITY_NAME = "tableOrder";

    private final TableOrderRepository tableOrderRepository;

    public TableOrderResource(TableOrderRepository tableOrderRepository) {
        this.tableOrderRepository = tableOrderRepository;
    }

    /**
     * {@code POST  /table-orders} : Create a new tableOrder.
     *
     * @param tableOrder the tableOrder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableOrder, or with status {@code 400 (Bad Request)} if the tableOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-orders")
    public ResponseEntity<TableOrder> createTableOrder(@RequestBody TableOrder tableOrder) throws URISyntaxException {
        log.debug("REST request to save TableOrder : {}", tableOrder);
        TableOrder result = tableOrderRepository.save(tableOrder);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /table-orders/:id} : Updates an existing tableOrder.
     *
     * @param id the id of the tableOrder to save.
     * @param tableOrder the tableOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableOrder,
     * or with status {@code 400 (Bad Request)} if the tableOrder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-orders/{id}")
    public ResponseEntity<?> updateTableOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableOrder tableOrder
    ) throws URISyntaxException {
        log.debug("REST request to update TableOrder : {}, {}", id, tableOrder);

        if (!tableOrderRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("entity not found");
        }

        TableOrder result = tableOrderRepository.save(tableOrder);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /table-orders} : get all the tableOrders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableOrders in body.
     */
    @GetMapping("/table-orders")
    public List<TableOrder> getAllTableOrders() {
        log.debug("REST request to get all TableOrders");
        return tableOrderRepository.findAll();
    }

    /**
     * {@code GET  /table-orders/:id} : get the "id" tableOrder.
     *
     * @param id the id of the tableOrder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableOrder, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-orders/{id}")
    public ResponseEntity<TableOrder> getTableOrder(@PathVariable Long id) {
        log.debug("REST request to get TableOrder : {}", id);
        Optional<TableOrder> tableOrder = tableOrderRepository.findById(id);
        return ResponseEntity.of(tableOrder);
    }

    /**
     * {@code DELETE  /table-orders/:id} : delete the "id" tableOrder.
     *
     * @param id the id of the tableOrder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-orders/{id}")
    public ResponseEntity<Void> deleteTableOrder(@PathVariable Long id) {
        log.debug("REST request to delete TableOrder : {}", id);
        tableOrderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
