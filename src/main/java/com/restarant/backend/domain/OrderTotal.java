package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrderTotal.
 */
@Entity
@Table(name = "order_total")
public class OrderTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "voucher")
    private Long voucher;

    @Column(name = "amount_totail", precision = 21, scale = 2)
    private BigDecimal amountTotail;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @OneToMany(mappedBy = "orderTotal")
    @JsonIgnoreProperties(value = { "orderDetails", "tables", "orderTotal" }, allowSetters = true)
    private Set<TableOrder> tableOrders = new HashSet<>();

    @JsonIgnoreProperties(value = { "orderTotal" }, allowSetters = true)
    @OneToOne(mappedBy = "orderTotal")
    private Payment payment;

    @ManyToOne
    @JsonIgnoreProperties(value = { "orderTotals", "vouchers" }, allowSetters = true)
    private Customer customer;

    @ManyToOne
    @JsonIgnoreProperties(value = { "orderTotals" }, allowSetters = true)
    private Staff staff;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderTotal id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoucher() {
        return this.voucher;
    }

    public OrderTotal voucher(Long voucher) {
        this.setVoucher(voucher);
        return this;
    }

    public void setVoucher(Long voucher) {
        this.voucher = voucher;
    }

    public BigDecimal getAmountTotail() {
        return this.amountTotail;
    }

    public OrderTotal amountTotail(BigDecimal amountTotail) {
        this.setAmountTotail(amountTotail);
        return this;
    }

    public void setAmountTotail(BigDecimal amountTotail) {
        this.amountTotail = amountTotail;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public OrderTotal deleteflag(Long deleteflag) {
        this.setDeleteflag(deleteflag);
        return this;
    }

    public void setDeleteflag(Long deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Set<TableOrder> getTableOrders() {
        return this.tableOrders;
    }

    public void setTableOrders(Set<TableOrder> tableOrders) {
        if (this.tableOrders != null) {
            this.tableOrders.forEach(i -> i.setOrderTotal(null));
        }
        if (tableOrders != null) {
            tableOrders.forEach(i -> i.setOrderTotal(this));
        }
        this.tableOrders = tableOrders;
    }

    public OrderTotal tableOrders(Set<TableOrder> tableOrders) {
        this.setTableOrders(tableOrders);
        return this;
    }

    public OrderTotal addTableOrder(TableOrder tableOrder) {
        this.tableOrders.add(tableOrder);
        tableOrder.setOrderTotal(this);
        return this;
    }

    public OrderTotal removeTableOrder(TableOrder tableOrder) {
        this.tableOrders.remove(tableOrder);
        tableOrder.setOrderTotal(null);
        return this;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        if (this.payment != null) {
            this.payment.setOrderTotal(null);
        }
        if (payment != null) {
            payment.setOrderTotal(this);
        }
        this.payment = payment;
    }

    public OrderTotal payment(Payment payment) {
        this.setPayment(payment);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderTotal customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Staff getStaff() {
        return this.staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public OrderTotal staff(Staff staff) {
        this.setStaff(staff);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderTotal)) {
            return false;
        }
        return id != null && id.equals(((OrderTotal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderTotal{" +
            "id=" + getId() +
            ", voucher=" + getVoucher() +
            ", amountTotail=" + getAmountTotail() +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
