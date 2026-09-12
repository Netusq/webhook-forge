package com.netus.webhookforge.webhookendpoint.dto;

import java.util.UUID;

public record WebhookEndpointResponse(
        UUID id,
        String name
) {
}
