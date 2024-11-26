package org.elebras.gesbatiments.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elebras.gesbatiments.model.Batiment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe BatimentJsonFactory fournit des méthodes pour gérer la sérialisation et la désérialisation des objets {@link Batiment}
 * en format JSON à l'aide de la bibliothèque Jackson.
 * Elle permet de créer un fichier JSON contenant une liste de bâtiments et de lire un fichier JSON pour créer une liste de bâtiments.
 */
public class BatimentJsonFactory {

    /**
     * Sérialise une liste de bâtiments et l'écrit dans un fichier JSON.
     *
     * @param file      Le fichier dans lequel les bâtiments seront écrits.
     * @param batiments La liste des bâtiments à sérialiser.
     */
    public void creerJsonListeBatiments(File file, List<Batiment> batiments) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, batiments);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Désérialise un fichier JSON en une liste de bâtiments.
     *
     * @param file Le fichier JSON à lire.
     * @return Une liste d'objets {@link Batiment} désérialisée à partir du fichier JSON.
     */
    public List<Batiment> creerListeBatimentsByJson(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Batiment> batiments = new ArrayList<>();
        try {
            batiments = objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println(e);
        }

        return batiments;
    }
}
