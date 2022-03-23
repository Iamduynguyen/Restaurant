package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Food.
 */
@Entity
@Table(name = "food")
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "jhi_create")
    private LocalDate create;

    @Column(name = "name")
    private String name;

    @Column(name = "views")
    private Long views;

    @Column(name = "notes")
    private Long notes;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @OneToMany(mappedBy = "food")
    @JsonIgnoreProperties(value = { "food" }, allowSetters = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "food")
    @JsonIgnoreProperties(value = { "foodMedias", "orderDetails", "food" }, allowSetters = true)
    private Set<FoodDetalls> foodDetalls = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "foods" }, allowSetters = true)
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Food id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Food title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreate() {
        return this.create;
    }

    public Food create(LocalDate create) {
        this.setCreate(create);
        return this;
    }

    public void setCreate(LocalDate create) {
        this.create = create;
    }

    public String getName() {
        return this.name;
    }

    public Food name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getViews() {
        return this.views;
    }

    public Food views(Long views) {
        this.setViews(views);
        return this;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public Food deleteflag(Long deleteflag) {
        this.setDeleteflag(deleteflag);
        return this;
    }

    public void setDeleteflag(Long deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        if (this.comments != null) {
            this.comments.forEach(i -> i.setFood(null));
        }
        if (comments != null) {
            comments.forEach(i -> i.setFood(this));
        }
        this.comments = comments;
    }

    public Food comments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public Food addComment(Comment comment) {
        this.comments.add(comment);
        comment.setFood(this);
        return this;
    }

    public Food removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setFood(null);
        return this;
    }

    public Set<FoodDetalls> getFoodDetalls() {
        return this.foodDetalls;
    }

    public void setFoodDetalls(Set<FoodDetalls> foodDetalls) {
        if (this.foodDetalls != null) {
            this.foodDetalls.forEach(i -> i.setFood(null));
        }
        if (foodDetalls != null) {
            foodDetalls.forEach(i -> i.setFood(this));
        }
        this.foodDetalls = foodDetalls;
    }

    public Food foodDetalls(Set<FoodDetalls> foodDetalls) {
        this.setFoodDetalls(foodDetalls);
        return this;
    }

    public Food addFoodDetalls(FoodDetalls foodDetalls) {
        this.foodDetalls.add(foodDetalls);
        foodDetalls.setFood(this);
        return this;
    }

    public Food removeFoodDetalls(FoodDetalls foodDetalls) {
        this.foodDetalls.remove(foodDetalls);
        foodDetalls.setFood(null);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Food category(Category category) {
        this.setCategory(category);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        return id != null && id.equals(((Food) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getNotes() {
        return notes;
    }

    public void setNotes(Long notes) {
        this.notes = notes;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Food{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", create='" + getCreate() + "'" +
            ", name='" + getName() + "'" +
            ", views=" + getViews() +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
