package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "deposit", precision = 21, scale = 2)
    private BigDecimal deposit;

    @Column(name = "status")
    private Long status;

    @JsonIgnoreProperties(value = { "tableOrders", "payment", "customer", "staff" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private OrderTotal orderTotal;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Payment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDeposit() {
        return this.deposit;
    }

    public Payment deposit(BigDecimal deposit) {
        this.setDeposit(deposit);
        return this;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Long getStatus() {
        return this.status;
    }

    public Payment status(Long status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public OrderTotal getOrderTotal() {
        return this.orderTotal;
    }

    public void setOrderTotal(OrderTotal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Payment orderTotal(OrderTotal orderTotal) {
        this.setOrderTotal(orderTotal);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", deposit=" + getDeposit() +
            ", status=" + getStatus() +
            "}";
    }
}
