package com.example.demo.core.model.entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutobotRepository extends JpaRepository<Autobot,Autobot> {
}

