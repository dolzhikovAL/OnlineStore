package com.DA.store.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;


@Entity
@NoArgsConstructor

@Table(name="users")
public @Data class User extends BaseEntity {
    @NotEmpty
    @Column
    private String password;
    @NotEmpty

    @Column
    private String email;
    @Column (name="first_name")

    @NotEmpty
    private String firstNme;
    @Column(name="last_name")

    @NotEmpty
    private String lastName;
    @Column(name="user_role")

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return firstNme.equals(user.firstNme) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstNme,lastName,email);
    }

}
