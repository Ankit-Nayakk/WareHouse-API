package com.example.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class RackedBlock extends Block{
    @OneToMany(mappedBy = "rackedBlock")
    private List<Rack> racks;
}
