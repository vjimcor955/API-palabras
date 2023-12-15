package com.example.apipalabras.controlador;

import com.example.apipalabras.modelos.Jugador;
import com.example.apipalabras.repos.JugadorRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorJugador {

    private final JugadorRepositorio jugadorRepositorio;

    public ControladorJugador(JugadorRepositorio jugadorRepositorio) {
        this.jugadorRepositorio = jugadorRepositorio;
    }

    // TODO: Terminar CRUD jugadores
    @GetMapping("/jugadores")
    public List<Jugador> getAllJugadores() {
        List<Jugador> allJugadores = jugadorRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allJugadores).getBody();
    }
}
