package com.example.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.Estante;

@Repository
public interface EstanteRepository extends JpaRepository<Estante, Long> {

}
