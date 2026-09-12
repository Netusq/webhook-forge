package com.netus.webhookforge.webhookevent;

import com.netus.webhookforge.webhookendpoint.EndpointNotFoundException;
import com.netus.webhookforge.webhookendpoint.WebhookEndpoint;
import com.netus.webhookforge.webhookendpoint.WebhookEndpointRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
class WebhookEventService {

    private final WebhookEventRepository eventRepository;
    private final WebhookEndpointRepository endpointRepository;

    WebhookEventService(WebhookEventRepository eventRepository, WebhookEndpointRepository endpointRepository) {
        this.eventRepository = eventRepository;
        this.endpointRepository = endpointRepository;
    }

    @Transactional
    public void receiveEvent(UUID token, String body){
        WebhookEndpoint endpoint = endpointRepository.findById(token)
                .orElseThrow(() -> new EndpointNotFoundException(token));

        WebhookEvent created = new WebhookEvent(endpoint, body);

        eventRepository.save(created);
    }
}
