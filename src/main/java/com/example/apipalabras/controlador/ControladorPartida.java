package com.example.apipalabras.controlador;

import com.example.apipalabras.modelos.Equipo;
import com.example.apipalabras.modelos.Partida;
import com.example.apipalabras.repos.EquipoRepositorio;
import com.example.apipalabras.repos.PartidaRepositorio;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorPartida {

    private final PartidaRepositorio partidaRepositorio;

    public ControladorPartida(PartidaRepositorio partidaRepositorio) {
        this.partidaRepositorio = partidaRepositorio;
    }

    @GetMapping("/partidas")
    public List<Partida> getAllEquipos() {
        List<Partida> allPartidas = partidaRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allPartidas).getBody();
    }

    // TODO: Terminar POST, PUT, DELETE partidas

    @PostMapping("/partidas")
    public ResponseEntity<Partida> postPartida(@Valid @RequestBody Partida partida) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaRepositorio.save(partida));
    }

}
