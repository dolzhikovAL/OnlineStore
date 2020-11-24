package com.DA.store.model;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");

    private String role;

    public String getRole() {
        return role;
    }

    UserRole(String role) {
        this.role = role;
    }

    public static Optional<UserRole> getUserRole(String status) {
        return Arrays.stream(UserRole.values())
                .filter(enumValue -> enumValue.getRole().equals(status))
                .findAny();
    }
}
