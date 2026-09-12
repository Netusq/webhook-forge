package com.netus.webhookforge.webhookevent;

import org.springframework.data.jpa.repository.JpaRepository;

interface WebhookEventRepository extends JpaRepository<WebhookEvent, Long> {
}
