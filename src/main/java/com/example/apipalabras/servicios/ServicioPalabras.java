package com.example.apipalabras.servicios;

import com.example.apipalabras.modelos.Palabra;
import com.example.apipalabras.repos.PalabrasRepositorio;
import java.io.*;
import java.util.*;

public class ServicioPalabras {

    private final PalabrasRepositorio palabrasRepositorio;

    public ServicioPalabras(PalabrasRepositorio palabrasRepositorio) {
        this.palabrasRepositorio = palabrasRepositorio;
    }

    public List<Optional<Palabra>> palabrasRandom(int size) {
        var random = new Random();
        List<Palabra> allPalabras = palabrasRepositorio.findAll();
        List<Optional<Palabra>> palabrasRandom = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Long id = random.nextLong((allPalabras.size()+1)-1)+1;
            palabrasRandom.add(palabrasRepositorio.findById(id));
        }
        return palabrasRandom;
    }

    public List<String> prefijosToList(String path) {
        List<String> valuesList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String value = scanner.next();
                valuesList.add(value.substring(0, value.length() - 1));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return valuesList;
    }

    public List<String> sufijosToList(String path) {
        List<String> valuesList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String value = scanner.next();
                valuesList.add(value.substring(1));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return valuesList;
    }
}
