package org.elebras.gesbatiments.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BatimentTest {

    @Test
    void testConstructorAndGetters() {
        Piece piece1 = new Piece(20, true, 0, null);
        Piece piece2 = new Piece(15, false, 1, null);
        Etage etage1 = new Etage(1);
        Etage etage2 = new Etage(2);

        Batiment batiment = new Batiment(1, "Immeuble A", "Bureau",
                List.of(piece1, piece2), List.of(etage1, etage2));

        assertEquals(1, batiment.getNumero());
        assertEquals("Immeuble A", batiment.getNom());
        assertEquals("Bureau", batiment.getUsage());
        assertEquals(2, batiment.getPieces().size());
        assertEquals(2, batiment.getEtages().size());
    }

    @Test
    void testSetters() {
        Batiment batiment = new Batiment(null, null, null, null, null);

        batiment.setNumero(2);
        batiment.setNom("Immeuble B");
        batiment.setUsage("Résidentiel");
        batiment.setPieces(List.of(new Piece(30, true, 2, null)));
        batiment.setEtages(List.of(new Etage(3)));

        assertEquals(2, batiment.getNumero());
        assertEquals("Immeuble B", batiment.getNom());
        assertEquals("Résidentiel", batiment.getUsage());
        assertEquals(1, batiment.getPieces().size());
        assertEquals(1, batiment.getEtages().size());
    }

    @Test
    void testEqualsAndHashCode() {
        Batiment batiment1 = new Batiment(1, "Immeuble A", "Bureau", null, null);
        Batiment batiment2 = new Batiment(1, "Immeuble A", "Résidentiel", null, null);
        Batiment batiment3 = new Batiment(2, "Immeuble C", "Education", null, null);

        assertEquals(batiment1, batiment2);
        assertNotEquals(batiment1, batiment3);
        assertEquals(batiment1.hashCode(), batiment2.hashCode());
    }
}
