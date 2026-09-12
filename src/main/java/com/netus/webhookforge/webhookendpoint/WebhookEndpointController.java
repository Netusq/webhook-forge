package com.netus.webhookforge.webhookendpoint;

import com.netus.webhookforge.webhookendpoint.dto.CreateEndpointRequest;
import com.netus.webhookforge.webhookendpoint.dto.WebhookEndpointResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
class WebhookEndpointController {
    private final WebhookEndpointService service;

    @PostMapping("/api/v1/endpoints")
    public ResponseEntity<WebhookEndpointResponse> createEndpoint(
            @RequestBody @Valid CreateEndpointRequest request
    ) {
        WebhookEndpointResponse response =
                service.createEndpoint(request.name());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/api/v1/endpoints/{id}")
    public ResponseEntity<?> getEndpointByToken(@PathVariable String id){
        if (!isValidUuidV7(id)){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка: Передан неверный формат токена. Ожидается UUIDv7");
        }

        UUID token = UUID.fromString(id);

        try{
            WebhookEndpointResponse response = service.getEndpointById(token);
            return ResponseEntity.ok(response);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    private boolean isValidUuidV7(String uuidStr) {
        try {
            if (uuidStr == null || uuidStr.length() != 36) {
                return false;
            }
            UUID uuid = UUID.fromString(uuidStr);
            return uuid.version() == 7;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
