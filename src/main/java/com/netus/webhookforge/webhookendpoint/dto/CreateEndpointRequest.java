package com.netus.webhookforge.webhookendpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEndpointRequest(
        @NotBlank(message = "имя эндпоинта не может быть пустым!")
        @Size(min = 3, max = 100, message = "имя должно быть от 3 до 100 символов")
        String name
) {
}
