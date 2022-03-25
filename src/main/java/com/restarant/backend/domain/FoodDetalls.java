package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A FoodDetalls.
 */
@Entity
@Table(name = "food_detalls")
public class FoodDetalls implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "foodsize")
    private String foodsize;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @OneToMany(mappedBy = "foodDetalls",fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "foodDetalls" }, allowSetters = true)
    private Set<FoodMedia> foodMedias = new HashSet<>();

    @OneToMany(mappedBy = "foodDetalls")
    @JsonIgnoreProperties(value = { "foodDetalls", "tableOrder" }, allowSetters = true)
    private Set<OrderDetails> orderDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments", "foodDetalls", "category" }, allowSetters = true)
    private Food food;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FoodDetalls id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodsize() {
        return this.foodsize;
    }

    public FoodDetalls foodsize(String foodsize) {
        this.setFoodsize(foodsize);
        return this;
    }

    public void setFoodsize(String foodsize) {
        this.foodsize = foodsize;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public FoodDetalls amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public FoodDetalls deleteflag(Long deleteflag) {
        this.setDeleteflag(deleteflag);
        return this;
    }

    public void setDeleteflag(Long deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Set<FoodMedia> getFoodMedias() {
        return this.foodMedias;
    }

    public void setFoodMedias(Set<FoodMedia> foodMedias) {
        if (this.foodMedias != null) {
            this.foodMedias.forEach(i -> i.setFoodDetalls(null));
        }
        if (foodMedias != null) {
            foodMedias.forEach(i -> i.setFoodDetalls(this));
        }
        this.foodMedias = foodMedias;
    }

    public FoodDetalls foodMedias(Set<FoodMedia> foodMedias) {
        this.setFoodMedias(foodMedias);
        return this;
    }

    public FoodDetalls addFoodMedia(FoodMedia foodMedia) {
        this.foodMedias.add(foodMedia);
        foodMedia.setFoodDetalls(this);
        return this;
    }

    public FoodDetalls removeFoodMedia(FoodMedia foodMedia) {
        this.foodMedias.remove(foodMedia);
        foodMedia.setFoodDetalls(null);
        return this;
    }

    public Set<OrderDetails> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        if (this.orderDetails != null) {
            this.orderDetails.forEach(i -> i.setFoodDetalls(null));
        }
        if (orderDetails != null) {
            orderDetails.forEach(i -> i.setFoodDetalls(this));
        }
        this.orderDetails = orderDetails;
    }

    public FoodDetalls orderDetails(Set<OrderDetails> orderDetails) {
        this.setOrderDetails(orderDetails);
        return this;
    }

    public FoodDetalls addOrderDetails(OrderDetails orderDetails) {
        this.orderDetails.add(orderDetails);
        orderDetails.setFoodDetalls(this);
        return this;
    }

    public FoodDetalls removeOrderDetails(OrderDetails orderDetails) {
        this.orderDetails.remove(orderDetails);
        orderDetails.setFoodDetalls(null);
        return this;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public FoodDetalls food(Food food) {
        this.setFood(food);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodDetalls)) {
            return false;
        }
        return id != null && id.equals(((FoodDetalls) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodDetalls{" +
            "id=" + getId() +
            ", foodsize='" + getFoodsize() + "'" +
            ", amount=" + getAmount() +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
