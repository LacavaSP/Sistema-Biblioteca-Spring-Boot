package com.example.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.Locatario;

@Repository
public interface LocatarioRepository extends JpaRepository<Locatario, String> {

}
