package com.example.apipalabras.controlador;

import com.example.apipalabras.error.EquipoNotFound;
import com.example.apipalabras.modelos.Equipo;
import com.example.apipalabras.repos.EquipoRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorEquipo {

    private final EquipoRepositorio equipoRepositorio;

    public ControladorEquipo(EquipoRepositorio equipoRepositorio) {
        this.equipoRepositorio = equipoRepositorio;
    }

    /**
     * @return Todos los equipos
     */
    @GetMapping("/equipos")
    public List<Equipo> getAllEquipos() {
        List<Equipo> allEquipos = equipoRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allEquipos).getBody();
    }

    /**
     * @param equipo json con los datos del equipo a insertar
     * @return Inserta el equipo
     */
    @PostMapping("/equipo")
    public ResponseEntity<Equipo> postEquipo(@Valid @RequestBody Equipo equipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoRepositorio.save(equipo));
    }

    /**
     * @param id Id del equipo a modificar
     * @param equipo json con los datos del equipo a modificar
     * @return Modifica el equipo
     */
    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@PathVariable Long id, @Valid @RequestBody Equipo equipo) {
        return equipoRepositorio.findById(id)
                .map(existingEquipo -> {
                    existingEquipo.setId(equipo.getId());
                    existingEquipo.setName(equipo.getName());
                    existingEquipo.setScore(equipo.getScore());
                    existingEquipo.setBadge(equipo.getBadge());
                    return ResponseEntity.status(HttpStatus.OK).body(equipoRepositorio.save(existingEquipo)).getBody();
                })
                .orElseThrow(() ->  new EquipoNotFound(id));
    }

    /**
     * @param id Id del equipo a eliminar
     * @return Elimina el equipo
     */
    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id) {
        return equipoRepositorio.findById(id)
                .map(equipo -> {
                    equipoRepositorio.delete(equipo);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new EquipoNotFound(id));
    }
}
