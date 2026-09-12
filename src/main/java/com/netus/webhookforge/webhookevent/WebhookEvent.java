package com.netus.webhookforge.webhookevent;

import com.netus.webhookforge.webhookendpoint.WebhookEndpoint;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "webhook_events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WebhookEvent {
    @Id
    @SequenceGenerator(name = "webhook_event_seq_gen", sequenceName = "webhook_event_seq", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "webhook_event_seq_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WebhookEndpoint endpoint;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public WebhookEvent(WebhookEndpoint endpoint, String body){
        this.endpoint = endpoint;
        this.body = body;
    }
}
