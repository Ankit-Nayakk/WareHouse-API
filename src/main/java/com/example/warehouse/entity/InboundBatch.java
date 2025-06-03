package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "inbound_batch")
@Inheritance(strategy = InheritanceType.JOINED)
public class InboundBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "batch_id", nullable = false, updatable = false)
    private String batchId;

    @Column(name = "count_of_rejected_unit", nullable = false)
    private int countOfRejectedUnit;

    @Column(name = "count_of_accepted_unit", nullable = false)
    private int countOfAcceptedUnit;

    @OneToMany(mappedBy = "inboundBatch")
    private List<ProductUnit> productUnits;

    @ManyToOne
    @JoinColumn(name = "inbound_shipment_id")
    private InboundShipment inboundShipment;

}
