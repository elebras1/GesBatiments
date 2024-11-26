package org.elebras.gesbatiments;

import org.elebras.gesbatiments.facade.Campus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampusTest {
    private Campus campus;

    @BeforeEach
    public void init() {
        this.campus = new Campus();
    }

    @Test
    public void contruireBatimentTest() {
        assertNotNull(this.campus.ajouterBatiment("Batiment A", 9, 2, 18));
    }

    @Test
    public void verificationBatimentTest() {
        int numeroBatiment = this.campus.ajouterBatiment("Batiment A", 9, 2, 18);
        assertTrue(this.campus.verification(numeroBatiment));
    }

    @Test
    public void detruireBatimentTest() {
        int numeroBatiment = this.campus.ajouterBatiment("Batiment A", 9, 2, 18);
        assertTrue(this.campus.supprimeBatiment(numeroBatiment));
    }

    @Test
    public void modifierNomBatimentTest() {
        int numeroBatiment = this.campus.ajouterBatiment("Batiment A", 9, 2, 18);
        assertEquals("Batiment AP", this.campus.modifierNomBatiment(numeroBatiment, "Batiment AP"));
    }
}
