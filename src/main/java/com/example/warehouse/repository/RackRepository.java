package com.example.warehouse.repository;

import com.example.warehouse.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RackRepository extends JpaRepository<Rack, String> {
}
