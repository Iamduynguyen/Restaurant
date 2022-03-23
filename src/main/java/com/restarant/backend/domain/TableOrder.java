package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TableOrder.
 */
@Entity
@Table(name = "table_order")
public class TableOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @OneToMany(mappedBy = "tableOrder")
    @JsonIgnoreProperties(value = { "foodDetalls", "tableOrder" }, allowSetters = true)
    private Set<OrderDetails> orderDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "tableOrders" }, allowSetters = true)
    private Tables tables;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tableOrders", "payment", "customer", "staff" }, allowSetters = true)
    private OrderTotal orderTotal;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TableOrder id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public TableOrder deleteflag(Long deleteflag) {
        this.setDeleteflag(deleteflag);
        return this;
    }

    public void setDeleteflag(Long deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Set<OrderDetails> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        if (this.orderDetails != null) {
            this.orderDetails.forEach(i -> i.setTableOrder(null));
        }
        if (orderDetails != null) {
            orderDetails.forEach(i -> i.setTableOrder(this));
        }
        this.orderDetails = orderDetails;
    }

    public TableOrder orderDetails(Set<OrderDetails> orderDetails) {
        this.setOrderDetails(orderDetails);
        return this;
    }

    public TableOrder addOrderDetails(OrderDetails orderDetails) {
        this.orderDetails.add(orderDetails);
        orderDetails.setTableOrder(this);
        return this;
    }

    public TableOrder removeOrderDetails(OrderDetails orderDetails) {
        this.orderDetails.remove(orderDetails);
        orderDetails.setTableOrder(null);
        return this;
    }

    public Tables getTables() {
        return this.tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public TableOrder tables(Tables tables) {
        this.setTables(tables);
        return this;
    }

    public OrderTotal getOrderTotal() {
        return this.orderTotal;
    }

    public void setOrderTotal(OrderTotal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public TableOrder orderTotal(OrderTotal orderTotal) {
        this.setOrderTotal(orderTotal);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableOrder)) {
            return false;
        }
        return id != null && id.equals(((TableOrder) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TableOrder{" +
            "id=" + getId() +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
