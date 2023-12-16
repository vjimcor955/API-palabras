package com.example.apipalabras.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JugadorNotFound extends RuntimeException {

    public JugadorNotFound(Long id) {
        super ("Jugador no encontrado con el id " + id);
    }

}