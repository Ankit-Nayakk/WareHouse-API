package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_unit")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "unit_id", nullable = false, updatable = false)
    private String unitId;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "inbound_shipment", nullable = false)
    private InboundShipment inboundShipment;

    @ManyToOne
    @JoinColumn(name = "inbound_batch", nullable = false)
    private InboundBatch inboundBatch;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


}
