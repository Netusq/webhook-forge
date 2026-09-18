package com.netus.webhookforge.webhookevent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface WebhookEventRepository extends JpaRepository<WebhookEvent, Long> {
    List<WebhookEvent> findAllByEndpoint_Id(UUID endpointId);
}
