package com.example.apipalabras.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "juego")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum DificultadJuego {
        facil,
        normal,
        dificil
    }

    @Enumerated(EnumType.STRING)
    private DificultadJuego difficulty_j;
    private int max_tries;
    private String description;
}