package com.netus.webhookforge.webhookendpoint;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "webhook_endpoints")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class WebhookEndpoint {
    @Id
    @Generated(event = EventType.INSERT)
    @Column(name = "id", columnDefinition = "UUID DEFAULT uuidv7()")
    private UUID id;

    @NotBlank
    @Setter
    private String name;

    public WebhookEndpoint(String name) {
        this.name = name;
    }
}
