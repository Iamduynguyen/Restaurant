package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A FoodMedia.
 */
@Entity
@Table(name = "food_media")
public class FoodMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "foodurl")
    private String foodurl;

    @Column(name = "foodtype")
    private String foodtype;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "foodMedias", "orderDetails", "food" }, allowSetters = true)
    private FoodDetalls foodDetalls;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FoodMedia id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodurl() {
        return this.foodurl;
    }

    public FoodMedia foodurl(String foodurl) {
        this.setFoodurl(foodurl);
        return this;
    }

    public void setFoodurl(String foodurl) {
        this.foodurl = foodurl;
    }

    public String getFoodtype() {
        return this.foodtype;
    }

    public FoodMedia foodtype(String foodtype) {
        this.setFoodtype(foodtype);
        return this;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public FoodMedia deleteflag(Long deleteflag) {
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

    public FoodMedia foodDetalls(FoodDetalls foodDetalls) {
        this.setFoodDetalls(foodDetalls);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodMedia)) {
            return false;
        }
        return id != null && id.equals(((FoodMedia) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodMedia{" +
            "id=" + getId() +
            ", foodurl='" + getFoodurl() + "'" +
            ", foodtype='" + getFoodtype() + "'" +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
