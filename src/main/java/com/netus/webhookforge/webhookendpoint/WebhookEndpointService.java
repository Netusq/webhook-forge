package com.netus.webhookforge.webhookendpoint;

import com.netus.webhookforge.webhookendpoint.dto.WebhookEndpointResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
class WebhookEndpointService {
    private final WebhookEndpointRepository repository;

    WebhookEndpointService(WebhookEndpointRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public WebhookEndpointResponse createEndpoint(String name) {
        WebhookEndpoint endpoint = new WebhookEndpoint(name);
        WebhookEndpoint saved = repository.save(endpoint);

        return new WebhookEndpointResponse(saved.getId(), saved.getName());
    }

    @Transactional(readOnly = true)
    public WebhookEndpointResponse getEndpointById(UUID id){
        return repository.findById(id)
                .map(ednpoint -> new WebhookEndpointResponse(ednpoint.getId(), ednpoint.getName()))
                .orElseThrow(() -> new EndpointNotFoundException(id));
    }
}
