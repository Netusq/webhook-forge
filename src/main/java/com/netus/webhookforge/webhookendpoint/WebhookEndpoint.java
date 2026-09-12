package com.netus.webhookforge.webhookendpoint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.util.UUID;

@Entity
@Table(name = "webhook_endpoints")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebhookEndpoint {

    @Id
    @Generated(event = EventType.INSERT)
    @ColumnDefault("uuidv7()")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    public WebhookEndpoint(String name) {
        this.name = name;
    }
}