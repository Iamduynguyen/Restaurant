package com.restarant.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tables.
 */
@Entity
@Table(name = "jhi_tables")
public class Tables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "numberofchair")
    private Integer numberofchair;

    @Column(name = "deleteflag")
    private Long deleteflag;

    @OneToMany(mappedBy = "tables")
    @JsonIgnoreProperties(value = { "orderDetails", "tables", "orderTotal" }, allowSetters = true)
    private Set<TableOrder> tableOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Tables id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Tables status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumberofchair() {
        return this.numberofchair;
    }

    public Tables numberofchair(Integer numberofchair) {
        this.setNumberofchair(numberofchair);
        return this;
    }

    public void setNumberofchair(Integer numberofchair) {
        this.numberofchair = numberofchair;
    }

    public Long getDeleteflag() {
        return this.deleteflag;
    }

    public Tables deleteflag(Long deleteflag) {
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
            this.tableOrders.forEach(i -> i.setTables(null));
        }
        if (tableOrders != null) {
            tableOrders.forEach(i -> i.setTables(this));
        }
        this.tableOrders = tableOrders;
    }

    public Tables tableOrders(Set<TableOrder> tableOrders) {
        this.setTableOrders(tableOrders);
        return this;
    }

    public Tables addTableOrder(TableOrder tableOrder) {
        this.tableOrders.add(tableOrder);
        tableOrder.setTables(this);
        return this;
    }

    public Tables removeTableOrder(TableOrder tableOrder) {
        this.tableOrders.remove(tableOrder);
        tableOrder.setTables(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tables)) {
            return false;
        }
        return id != null && id.equals(((Tables) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tables{" +
            "id=" + getId() +
            ", status=" + getStatus() +
            ", numberofchair=" + getNumberofchair() +
            ", deleteflag=" + getDeleteflag() +
            "}";
    }
}
