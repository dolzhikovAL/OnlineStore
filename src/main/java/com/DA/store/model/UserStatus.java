package com.DA.store.model;

import java.util.Arrays;
import java.util.Optional;

public enum UserStatus {
    ACTIVE("ACTIVE"),
    NOT_ACTIVE("NOT_ACTIVE");

    private String status;

    public String getStatus() {
        return status;
    }

    UserStatus(String status) {
        this.status = status;
    }

    public static Optional<UserStatus> getUserStatus(String status) {
        return Arrays.stream(UserStatus.values())
                .filter(enumValue -> enumValue.getStatus().equals(status))
                .findAny();
    }
}
