package com.example.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
