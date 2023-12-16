package com.example.apipalabras.controlador;

import com.example.apipalabras.error.JugadorNotFound;
import com.example.apipalabras.modelos.Jugador;
import com.example.apipalabras.repos.JugadorRepositorio;
import jakarta.validation.Valid;
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

    @GetMapping("/jugadores")
    public List<Jugador> getAllJugadores() {
        List<Jugador> allJugadores = jugadorRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allJugadores).getBody();
    }

    @PostMapping("/jugador")
    public ResponseEntity<Jugador> postEquipo(@Valid @RequestBody Jugador jugador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jugadorRepositorio.save(jugador));
    }

    @PutMapping("/jugador/{id}")
    public Jugador updateEquipo(@PathVariable Long id, @Valid @RequestBody Jugador jugador) {
        return jugadorRepositorio.findById(id)
                .map(existingJugador -> {
                    existingJugador.setId(jugador.getId());
                    existingJugador.setUser(jugador.getUser());
                    existingJugador.setScore(jugador.getScore());
                    existingJugador.setAvatar_img(jugador.getAvatar_img());
                    existingJugador.setTeam_id(jugador.getTeam_id());
                    return ResponseEntity.status(HttpStatus.OK).body(jugadorRepositorio.save(existingJugador)).getBody();
                })
                .orElseThrow(() ->  new JugadorNotFound(id));
    }

    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id) {
        return jugadorRepositorio.findById(id)
                .map(jugador -> {
                    jugadorRepositorio.delete(jugador);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new JugadorNotFound(id));
    }
}
