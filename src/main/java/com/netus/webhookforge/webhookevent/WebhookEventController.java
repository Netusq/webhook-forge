package com.netus.webhookforge.webhookevent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
class WebhookEventController {

    private final WebhookEventService service;

    @PostMapping("/h/{token}")
    public ResponseEntity<Void> receiveWebhook(
            @PathVariable UUID token,
            @RequestBody String body
    ){
        service.receiveEvent(token, body);

        return  ResponseEntity.ok().build();
    }
}
