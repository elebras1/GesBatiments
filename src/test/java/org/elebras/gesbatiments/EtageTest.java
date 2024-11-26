package org.elebras.gesbatiments;

import org.elebras.gesbatiments.model.Etage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EtageTest {

    @Test
    void testConstructorAndGetter() {
        Etage etage = new Etage(1);
        assertEquals(1, etage.getNumero());
    }

    @Test
    void testSetter() {
        Etage etage = new Etage(null);
        etage.setNumero(2);
        assertEquals(2, etage.getNumero());
    }
}
