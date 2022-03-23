package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties(value = { "tableOrders", "payment", "customer", "staff" }, allowSetters = true)
    private Set<OrderTotal> orderTotals = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties(value = { "customer" }, allowSetters = true)
    private Set<Voucher> vouchers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Customer name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public Customer email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public Customer gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public Customer deleteflag(Long deleteflag) {
        this.setDeleteflag(deleteflag);
        return this;
    }

    public void setDeleteflag(Long deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Set<OrderTotal> getOrderTotals() {
        return this.orderTotals;
    }

    public void setOrderTotals(Set<OrderTotal> orderTotals) {
        if (this.orderTotals != null) {
            this.orderTotals.forEach(i -> i.setCustomer(null));
        }
        if (orderTotals != null) {
            orderTotals.forEach(i -> i.setCustomer(this));
        }
        this.orderTotals = orderTotals;
    }

    public Customer orderTotals(Set<OrderTotal> orderTotals) {
        this.setOrderTotals(orderTotals);
        return this;
    }

    public Customer addOrderTotal(OrderTotal orderTotal) {
        this.orderTotals.add(orderTotal);
        orderTotal.setCustomer(this);
        return this;
    }

    public Customer removeOrderTotal(OrderTotal orderTotal) {
        this.orderTotals.remove(orderTotal);
        orderTotal.setCustomer(null);
        return this;
    }

    public Set<Voucher> getVouchers() {
        return this.vouchers;
    }

    public void setVouchers(Set<Voucher> vouchers) {
        if (this.vouchers != null) {
            this.vouchers.forEach(i -> i.setCustomer(null));
        }
        if (vouchers != null) {
            vouchers.forEach(i -> i.setCustomer(this));
        }
        this.vouchers = vouchers;
    }

    public Customer vouchers(Set<Voucher> vouchers) {
        this.setVouchers(vouchers);
        return this;
    }

    public Customer addVoucher(Voucher voucher) {
        this.vouchers.add(voucher);
        voucher.setCustomer(this);
        return this;
    }

    public Customer removeVoucher(Voucher voucher) {
        this.vouchers.remove(voucher);
        voucher.setCustomer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", gender='" + getGender() + "'" +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
