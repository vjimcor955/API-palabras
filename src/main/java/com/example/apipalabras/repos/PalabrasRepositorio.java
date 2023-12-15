package com.example.apipalabras.repos;

import com.example.apipalabras.modelos.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalabrasRepositorio extends JpaRepository<Palabra, Long> {
}