package com.restarant.backend.service.dtoinput;

import com.restarant.backend.domain.FoodMedia;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

public class FoodDetailDtoInput {
    private Long id;
    private String foodsize;
    private BigDecimal discount;
    private BigDecimal amount;
    private Long foodid;
    private List<FoodMedia> foodMedias;

    public FoodDetailDtoInput() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodsize() {
        return foodsize;
    }

    public void setFoodsize(String foodsize) {
        this.foodsize = foodsize;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getFoodid() {
        return foodid;
    }

    public void setFoodid(Long foodid) {
        this.foodid = foodid;
    }

    public List<FoodMedia> getFoodMedias() {
        return foodMedias;
    }

    public void setFoodMedias(List<FoodMedia> foodMedias) {
        this.foodMedias = foodMedias;
    }

    @Override
    public String toString() {
        return "FoodDetailDtoInput{" +
                "id=" + id +
                ", foodsize='" + foodsize + '\'' +
                ", discount=" + discount +
                ", amount=" + amount +
                ", foodid=" + foodid +
                ", foodMedias=" + foodMedias +
                '}';
    }
}
