package com.example.apipalabras.controlador;

import com.example.apipalabras.error.PartidaNotFound;
import com.example.apipalabras.modelos.Partida;
import com.example.apipalabras.repos.PartidaRepositorio;
import jakarta.validation.Valid;
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

    /**
     * @return Todas las partidas
     */
    @GetMapping("/partidas")
    public List<Partida> getAllPartidas() {
        List<Partida> allPartidas = partidaRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allPartidas).getBody();
    }

    /**
     * @param partida json con los datos de la partida a insertar
     * @return Inserta la partida
     */
    @PostMapping("/partida")
    public ResponseEntity<Partida> postPartida(@Valid @RequestBody Partida partida) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaRepositorio.save(partida));
    }

    /**
     * @param id Id de la partida a modificar
     * @param partida json con los datos de la partida a modificar
     * @return Modifica la partida
     */
    @PutMapping("/partida/{id}")
    public Partida updatePartida(@PathVariable Long id, @Valid @RequestBody Partida partida) {
        return partidaRepositorio.findById(id)
                .map(existingPartida -> {
                    existingPartida.setId(partida.getId());
                    existingPartida.setWord(partida.getWord());
                    existingPartida.setN_try(partida.getN_try());
                    existingPartida.setScore(partida.getScore());
                    existingPartida.setDate_time(partida.getDate_time());
                    existingPartida.setJugador_id(partida.getJugador_id());
                    existingPartida.setJugador_Equipo_id(partida.getJugador_Equipo_id());
                    existingPartida.setJuego_id(partida.getJuego_id());
                    return ResponseEntity.status(HttpStatus.OK).body(partidaRepositorio.save(existingPartida)).getBody();
                })
                .orElseThrow(() ->  new PartidaNotFound(id));
    }

    /**
     * @param id Id de la partida a eliminar
     * @return Elimina la partida
     */
    @DeleteMapping("/partida/{id}")
    public ResponseEntity<?> deletePartida(@PathVariable Long id) {
        return partidaRepositorio.findById(id)
                .map(partida -> {
                    partidaRepositorio.delete(partida);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new PartidaNotFound(id));
    }
}
