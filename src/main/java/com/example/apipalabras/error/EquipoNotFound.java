package com.example.apipalabras.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipoNotFound extends RuntimeException {

    public EquipoNotFound(Long id) {
        super ("Equipo no encontrado con el id " + id);
    }

}