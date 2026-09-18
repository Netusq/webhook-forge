package com.netus.webhookforge.webhookevent;

import com.netus.webhookforge.common.validation.UuidV7Validator;
import com.netus.webhookforge.webhookevent.dto.WebhookEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
class WebhookEventController {

    private final WebhookEventService service;
    private final ObjectMapper objectMapper;

    @PostMapping("/h/{token}")
    public ResponseEntity<Void> receiveWebhook(
            @PathVariable UUID token,
            @RequestBody String body,
            @RequestHeader HttpHeaders headers,
            @RequestHeader(value = "Content-Type", required = false) String contentType

    ) throws JacksonException{
        String headersJson = objectMapper.writeValueAsString(headers);

        service.receiveEvent(token,
                body,
                headersJson,
                contentType,
                WebhookHttpMethod.POST);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/endpoints/{id}/events")
    public ResponseEntity<?> getEndpointEventsById(
            @PathVariable String id) {
        if (!UuidV7Validator.isValid(id)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка: передан не верный формат токена endpoint. Ожидается UUIDv7");
        }

        UUID token = UUID.fromString(id);

        try {
            ArrayList<WebhookEventResponse> responses = service.getEventsByEndpointId(token);
            return ResponseEntity.ok(responses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
