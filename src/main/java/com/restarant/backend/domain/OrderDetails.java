package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A OrderDetails.
 */
@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @ManyToOne
    @JsonIgnoreProperties(value = { "foodMedias", "orderDetails", "food" }, allowSetters = true)
    private FoodDetalls foodDetalls;

    @ManyToOne
    @JsonIgnoreProperties(value = { "orderDetails", "tables", "orderTotal" }, allowSetters = true)
    private TableOrder tableOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public OrderDetails quantity(Long quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public OrderDetails deleteflag(Long deleteflag) {
        this.setDeleteflag(deleteflag);
        return this;
    }

    public void setDeleteflag(Long deleteflag) {
        this.deleteflag = deleteflag;
    }

    public FoodDetalls getFoodDetalls() {
        return this.foodDetalls;
    }

    public void setFoodDetalls(FoodDetalls foodDetalls) {
        this.foodDetalls = foodDetalls;
    }

    public OrderDetails foodDetalls(FoodDetalls foodDetalls) {
        this.setFoodDetalls(foodDetalls);
        return this;
    }

    public TableOrder getTableOrder() {
        return this.tableOrder;
    }

    public void setTableOrder(TableOrder tableOrder) {
        this.tableOrder = tableOrder;
    }

    public OrderDetails tableOrder(TableOrder tableOrder) {
        this.setTableOrder(tableOrder);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDetails)) {
            return false;
        }
        return id != null && id.equals(((OrderDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDetails{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
