package org.elebras.gesbatiments;

import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void testConstructorAndGetters() {
        Etage etage = new Etage(1);
        Piece piece = new Piece(20, true, 1, etage);

        assertEquals(20, piece.getSurface());
        assertTrue(piece.getEstBureau());
        assertEquals(1, piece.getNumero());
        assertEquals(etage, piece.getEtage());
    }

    @Test
    void testSetters() {
        Piece piece = new Piece(null, null, null, null);

        piece.setSurface(25);
        piece.setEstBureau(false);
        piece.setNumero(2);
        piece.setEtage(new Etage(2));

        assertEquals(25, piece.getSurface());
        assertFalse(piece.getEstBureau());
        assertEquals(2, piece.getNumero());
        assertEquals(2, piece.getEtage().getNumero());
    }
}
