package com.example.warehouse.entity;

import com.example.warehouse.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "inbound_shipment")
@Inheritance(strategy = InheritanceType.JOINED)
public class InboundShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "shipment_id", nullable = false, updatable = false)
    private String shipmentId;

    @Column(name = "product_title", nullable = false)
    private String productTitle;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(name = "product_weight", nullable = false)
    private double productWeight;

    @Column(name = "product_length", nullable = false)
    private double productLength;

    @Column(name = "product_width", nullable = false)
    private double productWidth;

    @Column(name = "product_height", nullable = false)
    private double productHeight;

    @Column(name = "material_type", nullable = false)
    private String materialType;

    @Column(name = "care_instruction", nullable = false)
    private String careInstrucution;

    @Column(name = "seller_id", nullable = false)
    private String sellerId;

    @CreatedDate
    @Column(name = "created_at", nullable = false,updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse warehouse;

    @Column(name = "shipment_status", nullable = false)
    private ShipmentStatus shipmentStatus;
}
