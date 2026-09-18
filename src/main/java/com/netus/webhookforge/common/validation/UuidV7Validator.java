package com.netus.webhookforge.common.validation;

import java.util.UUID;

public final class UuidV7Validator {

    private UuidV7Validator() {
    }

    public static boolean isValid(String value) {
        try {
            if (value == null || value.length() != 36) {
                return false;
            }

            UUID uuid = UUID.fromString(value);
            return uuid.version() == 7;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}