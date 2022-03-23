package com.restarant.backend.web.restapi;

import com.restarant.backend.domain.OrderTotal;
import com.restarant.backend.repository.OrderTotalRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
@Transactional
public class OrderTotalResource {

    private final Logger log = LoggerFactory.getLogger(OrderTotalResource.class);

    private static final String ENTITY_NAME = "orderTotal";

    private final OrderTotalRepository orderTotalRepository;

    public OrderTotalResource(OrderTotalRepository orderTotalRepository) {
        this.orderTotalRepository = orderTotalRepository;
    }

    /**
     * {@code POST  /order-totals} : Create a new orderTotal.
     *
     * @param orderTotal the orderTotal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderTotal, or with status {@code 400 (Bad Request)} if the orderTotal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-totals")
    public ResponseEntity<OrderTotal> createOrderTotal(@RequestBody OrderTotal orderTotal) throws URISyntaxException {
        log.debug("REST request to save OrderTotal : {}", orderTotal);
        OrderTotal result = orderTotalRepository.save(orderTotal);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /order-totals/:id} : Updates an existing orderTotal.
     *
     * @param id the id of the orderTotal to save.
     * @param orderTotal the orderTotal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderTotal,
     * or with status {@code 400 (Bad Request)} if the orderTotal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderTotal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-totals/{id}")
    public ResponseEntity<?> updateOrderTotal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderTotal orderTotal
    ) throws URISyntaxException {
        log.debug("REST request to update OrderTotal : {}, {}", id, orderTotal);
        if (!orderTotalRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Entity not found");
        }
        OrderTotal result = orderTotalRepository.save(orderTotal);
        return ResponseEntity.ok().body(result);
    }


    /**
     * {@code GET  /order-totals} : get all the orderTotals.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderTotals in body.
     */
    @GetMapping("/order-totals")
    public List<OrderTotal> getAllOrderTotals(@RequestParam(required = false) String filter) {
        if ("payment-is-null".equals(filter)) {
            log.debug("REST request to get all OrderTotals where payment is null");
            return StreamSupport
                .stream(orderTotalRepository.findAll().spliterator(), false)
                .filter(orderTotal -> orderTotal.getPayment() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all OrderTotals");
        return orderTotalRepository.findAll();
    }

    /**
     * {@code GET  /order-totals/:id} : get the "id" orderTotal.
     *
     * @param id the id of the orderTotal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderTotal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-totals/{id}")
    public ResponseEntity<OrderTotal> getOrderTotal(@PathVariable Long id) {
        log.debug("REST request to get OrderTotal : {}", id);
        Optional<OrderTotal> orderTotal = orderTotalRepository.findById(id);
        return ResponseEntity.of(orderTotal);
    }

    /**
     * {@code DELETE  /order-totals/:id} : delete the "id" orderTotal.
     *
     * @param id the id of the orderTotal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-totals/{id}")
    public ResponseEntity<Void> deleteOrderTotal(@PathVariable Long id) {
        log.debug("REST request to delete OrderTotal : {}", id);
        orderTotalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
