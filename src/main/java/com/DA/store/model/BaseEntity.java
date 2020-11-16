package com.DA.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.GenerationType.*;

@MappedSuperclass
@NoArgsConstructor
public abstract @Data
class BaseEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    private java.util.UUID id;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseEntity{");
        sb.append("id=").append(id);
        sb.append("}");
        return sb.toString();
    }
}
