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

    /**
     * @param size Numero de palabras
     * @return Lista con el número de palabras aleatorias especificado
     */
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

    /**
     * @param path Archivo que contiene los prefijos
     * @return Lista con todos los prefijos
     */
    public List<String> prefijosToList(String path) {
        List<String> listaPrefijos = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String value = scanner.next();
                listaPrefijos.add(value.substring(0, value.length() - 1));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listaPrefijos;
    }

    /**
     * @param path Archivo que contiene los sufijos
     * @return Lista con todos los sufijos
     */
    public List<String> sufijosToList(String path) {
        List<String> listaSufijos = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String value = scanner.next();
                listaSufijos.add(value.substring(1));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listaSufijos;
    }
}
