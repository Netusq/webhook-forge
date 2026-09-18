package com.netus.webhookforge.webhookevent;

import com.netus.webhookforge.webhookendpoint.EndpointNotFoundException;
import com.netus.webhookforge.webhookendpoint.WebhookEndpoint;
import com.netus.webhookforge.webhookendpoint.WebhookEndpointRepository;
import com.netus.webhookforge.webhookevent.dto.WebhookEventResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    public void receiveEvent(UUID token, String body, String headers, String contentType, WebhookHttpMethod method) {
        WebhookEndpoint endpoint = endpointRepository.findById(token)
                .orElseThrow(() -> new EndpointNotFoundException(token));

        WebhookEvent created = new WebhookEvent(endpoint, body, headers, contentType, method);

        eventRepository.save(created);
    }

    public ArrayList<WebhookEventResponse> getEventsByEndpointId(UUID id) {
        WebhookEndpoint endpoint = endpointRepository.findById(id)
                .orElseThrow(() -> new EndpointNotFoundException(id));
        List<WebhookEvent> events =
                eventRepository.findAllByEndpoint_Id(endpoint.getId());

        return toResponseList(events);
    }

    private WebhookEventResponse toResponse(WebhookEvent event) {
        return new WebhookEventResponse(
                event.getId(),
                event.getEndpoint(),
                event.getBody(),
                event.getHeaders(),
                event.getContentType(),
                event.getMethod(),
                event.getCreatedAt()
        );
    }

    private ArrayList<WebhookEventResponse> toResponseList(List<WebhookEvent> events) {
        ArrayList<WebhookEventResponse> eventResponses = new ArrayList<>();

        for (WebhookEvent event : events) {
            eventResponses.add(toResponse(event));
        }

        return eventResponses;
    }
}
