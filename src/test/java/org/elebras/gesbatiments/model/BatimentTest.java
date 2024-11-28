package org.elebras.gesbatiments.model;

import org.elebras.gesbatiments.visiteur.BatimentVisiteur;
import org.elebras.gesbatiments.visiteur.Visiteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BatimentTest {

    private Batiment batiment;
    private Piece piece1;
    private Piece piece2;
    private Etage etage1;
    private Etage etage2;

    @BeforeEach
    void setUp() {
        this.etage1 = new Etage(1);
        this.etage2 = new Etage(2);
        this.piece1 = new Piece(12, true, 1, this.etage1);
        this.piece2 = new Piece(18, false, 2, this.etage2);
        this.batiment = new Batiment(1, "Batiment A", "Commercial", Arrays.asList(this.piece1, this.piece2), Arrays.asList(this.etage1, this.etage2));
    }

    @Test
    void testGetNumero() {
        assertEquals(1, this.batiment.getNumero());
    }

    @Test
    void testGetNom() {
        assertEquals("Batiment A", this.batiment.getNom());
    }

    @Test
    void testGetUsage() {
        assertEquals("Commercial", this.batiment.getUsage());
    }

    @Test
    void testGetPieces() {
        assertNotNull(this.batiment.getPieces());
        assertEquals(2, this.batiment.getPieces().size());
    }

    @Test
    void testGetEtages() {
        assertNotNull(this.batiment.getEtages());
        assertEquals(2, this.batiment.getEtages().size());
    }

    @Test
    void testEquals() {
        Batiment batiment2 = new Batiment(1, "Batiment A", "Commercial", Arrays.asList(this.piece1, this.piece2), Arrays.asList(this.etage1, this.etage2));
        assertEquals(this.batiment, batiment2);
    }

    @Test
    void testHashCode() {
        Batiment batiment2 = new Batiment(1, "Batiment A", "Commercial", Arrays.asList(this.piece1, this.piece2), Arrays.asList(this.etage1, this.etage2));
        assertEquals(this.batiment.hashCode(), batiment2.hashCode());
    }

    @Test
    void testAccept() {
        Visiteur visiteur = new BatimentVisiteur();
        this.batiment.accept(visiteur);
        String result = visiteur.getResult();
        assertTrue(result.contains("Bâtiment Batiment A"));
        assertTrue(result.contains("Etage 1"));
        assertTrue(result.contains("Pièce 1 - Surface: 12 m²"));
        assertTrue(result.contains("Etage 2"));
        assertTrue(result.contains("Pièce 2 - Surface: 18 m²"));
    }
}
