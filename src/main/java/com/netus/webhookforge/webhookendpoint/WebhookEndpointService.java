package com.netus.webhookforge.webhookendpoint;

import com.netus.webhookforge.webhookendpoint.dto.WebhookEndpointResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
class WebhookEndpointService {

    private final WebhookEndpointRepository repository;

    WebhookEndpointService(WebhookEndpointRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public WebhookEndpointResponse createEndpoint(String name) {
        WebhookEndpoint endpoint = new WebhookEndpoint(name);

        WebhookEndpoint saved = repository.save(endpoint);

        return toResponse(saved);
    }

    public WebhookEndpointResponse getEndpointById(UUID id) {
        WebhookEndpoint endpoint = repository.findById(id)
                .orElseThrow(() -> new EndpointNotFoundException(id));

        return toResponse(endpoint);
    }

    private WebhookEndpointResponse toResponse(WebhookEndpoint endpoint) {
        return new WebhookEndpointResponse(
                endpoint.getId(),
                endpoint.getName()
        );
    }
}