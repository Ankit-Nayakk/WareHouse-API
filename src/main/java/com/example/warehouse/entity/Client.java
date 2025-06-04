package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "organization_email", nullable = false)
    private String organizationEmail;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Column(name = "secret_key", nullable = false)
    private String secretKey;

    @CreatedDate
    @Column(name = "Registered_at", nullable = false, updatable = false)
    private Instant registeredAt;
}
