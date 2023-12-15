package com.example.apipalabras.repos;

import com.example.apipalabras.modelos.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepositorio extends JpaRepository<Jugador, Long> {
}
