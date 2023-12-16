package com.example.apipalabras.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidaNotFound extends RuntimeException {

    public PartidaNotFound(Long id) {
        super ("Partida no encontrada con el id " + id);
    }

}
