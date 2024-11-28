package org.elebras.gesbatiments.factory;

import org.elebras.gesbatiments.model.Batiment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BatimentJsonFactoryTest {

    private BatimentJsonFactory factory;

    @BeforeEach
    public void init() {
        this.factory = new BatimentJsonFactory();
    }

    @Test
    public void creerListeBatimentsByJsonTest() {
        File file = new File("batiments.json");

        List<Batiment> batiments = factory.creerListeBatimentsByJson(file);

        assertNotNull(batiments);
        assertFalse(batiments.isEmpty());
        assertEquals(3, batiments.size());

        assertEquals("A", batiments.get(0).getNom());
        assertEquals(1, batiments.get(0).getEtages().size());
        assertEquals(12, batiments.get(0).getPieces().size());
    }

    @Test
    public void creerListeBatimentsByJsonTestFichierVide() {
        File file = new File("batimentsVide.json");

        List<Batiment> batiments = factory.creerListeBatimentsByJson(file);

        assertNotNull(batiments);
        assertTrue(batiments.isEmpty());
    }

    @Test
    public void creerListeBatimentsByJsonTestFichierInvalide() {
        File file = new File("batimentNonOrdonne.json");

        List<Batiment> batiments = this.factory.creerListeBatimentsByJson(file);

        assertNotNull(batiments);
        assertTrue(batiments.isEmpty());
    }
}
