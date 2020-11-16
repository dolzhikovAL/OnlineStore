package com.DA.store.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.function.DoubleBinaryOperator;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="products")

public @Data
class Product extends BaseEntity{
@Column
   private String name;
@Column
private Double price;


@ManyToOne
    @JoinColumn(name="manufacturer_id")
    private Manufacturer manufacturer;
}
