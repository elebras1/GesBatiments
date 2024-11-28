package org.elebras.gesbatiments.model;

import org.elebras.gesbatiments.visiteur.BureauxVisiteur;
import org.elebras.gesbatiments.visiteur.Visiteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

  private Piece piece;
  private Etage etage;

  @BeforeEach
  void setUp() {
    this.etage = new Etage(1);
    this.piece = new Piece(20, true, 1, this.etage);
  }

  @Test
  void testGetSurface() {
    assertEquals(20, this.piece.getSurface());
  }

  @Test
  void testGetEstBureau() {
    assertTrue(this.piece.getEstBureau());
  }

  @Test
  void testGetNumero() {
    assertEquals(1, this.piece.getNumero());
  }

  @Test
  void testGetEtage() {
    assertNotNull(this.piece.getEtage());
    assertEquals(this.etage, this.piece.getEtage());
  }

  @Test
  void testAccept() {
    Visiteur visiteur = new BureauxVisiteur();
    this.piece.accept(visiteur);
    String result = visiteur.getResult();
    assertTrue(result.contains("Pièce 1"));
    assertTrue(result.contains("Type: Bureau"));
  }
}
