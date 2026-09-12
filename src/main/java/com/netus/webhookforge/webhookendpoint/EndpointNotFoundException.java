package com.netus.webhookforge.webhookendpoint;

import java.util.UUID;

public class EndpointNotFoundException extends RuntimeException {
    public EndpointNotFoundException(UUID id) {
        super("Эндоинт с id: " + id + "не найден");
    }
}
