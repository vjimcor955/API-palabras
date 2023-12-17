package com.example.apipalabras.controlador;

import com.example.apipalabras.modelos.Palabra;
import com.example.apipalabras.repos.PalabrasRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apipalabras.servicios.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ControladorPalabras {

    private final PalabrasRepositorio palabrasRepositorio;

    public ControladorPalabras(PalabrasRepositorio palabrasRepositorio) {
        this.palabrasRepositorio = palabrasRepositorio;
    }

    /**
     * @param page Numero de pagina
     * @param size Tamaño de la pagina
     * @return Todas las palabras paginadas
     */
    @GetMapping("/palabras")
    public Page<Palabra> getAllPalabras(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Palabra> palabraPage = palabrasRepositorio.findAll((pageRequest));
        return ResponseEntity.status(HttpStatus.OK).body(palabraPage).getBody();
    }

    /**
     * @param size Cantidad de palabras.
     * @return Cantidad de palabras aleatorias especificada.
     */
    @GetMapping("/palabra/random/{size}")
    public List<Optional<Palabra>> getPalabrasRandom(@PathVariable int size) {
        var servicioPalabras = new ServicioPalabras(palabrasRepositorio);
        var palabrasRandom = servicioPalabras.palabrasRandom(size);
        return ResponseEntity.status(HttpStatus.OK).body(palabrasRandom).getBody();
    }

    /**
     * @return Todos los prefijos y sufijos
     */
    @GetMapping("/palabras/cadenas")
    public Map<String, List<String>> getCadenas() {
        var servicioPalabras = new ServicioPalabras(palabrasRepositorio);
        var prefijos = servicioPalabras.prefijosToList("SQL/palabras/0_prefijos.txt");
        var sufijos = servicioPalabras.sufijosToList("SQL/palabras/0_subfijos.txt");
        Map<String, List<String>> cadenas = new HashMap<>();
        cadenas.put("prefijos", prefijos);
        cadenas.put("sufijos", sufijos);
        return ResponseEntity.status(HttpStatus.OK).body(cadenas).getBody();
    }

    /**
     * @param string Cadena
     * @return Palabras que contengan la cadena especificada
     */
    @GetMapping("/palabras/{string}")
    public Map<String, List<Palabra>> getPalabrasString(@PathVariable String string) {
        List<Palabra> allPalabras = palabrasRepositorio.findAll();
        Map<String, List<Palabra>> palabras = new HashMap<>();
        List<Palabra> palabraPrefijo = new ArrayList<>();
        List<Palabra> palabraSufijo = new ArrayList<>();
        for (Palabra palabra : allPalabras) {
            if (palabra.getPalabra().startsWith(string)) {
                palabraPrefijo.add(palabra);
            } else if (palabra.getPalabra().endsWith(string)) {
                palabraSufijo.add(palabra);
            }
        }
        palabras.put("prefijo", palabraPrefijo);
        palabras.put("sufijo", palabraSufijo);
        return ResponseEntity.status(HttpStatus.OK).body(palabras).getBody();
    }
}
