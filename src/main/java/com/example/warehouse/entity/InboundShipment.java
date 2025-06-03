package com.example.warehouse.entity;

import com.example.warehouse.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inbound_shipment")
@Inheritance(strategy = InheritanceType.JOINED)
public class InboundShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "shipment_id", nullable = false, updatable = false)
    private String shipmentId;

    @Column(name = "seller_id", nullable = false)
    private String sellerId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @CreatedDate
    @Column(name = "created_at", nullable = false,updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse warehouse;

    @Column(name = "shipment_status", nullable = false)
    private ShipmentStatus shipmentStatus;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
