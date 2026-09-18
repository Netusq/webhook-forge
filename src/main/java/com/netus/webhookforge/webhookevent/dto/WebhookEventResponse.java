package com.netus.webhookforge.webhookevent.dto;

import com.netus.webhookforge.webhookendpoint.WebhookEndpoint;
import com.netus.webhookforge.webhookevent.WebhookHttpMethod;

import java.time.Instant;

public record WebhookEventResponse(
        Long id,
        WebhookEndpoint endpoint,
        String body,
        String headers,
        String contentType,
        WebhookHttpMethod method,
        Instant createdAt
) {
}
