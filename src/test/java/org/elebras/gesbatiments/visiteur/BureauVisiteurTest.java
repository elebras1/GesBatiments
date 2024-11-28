package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BureauVisiteurTest {
  @Test
  void testVisiteBatiment() {
    Batiment batiment =
        new Batiment(1, "Bâtiment A", "Commercial", new ArrayList<>(), new ArrayList<>());
    BureauxVisiteur visiteur = new BureauxVisiteur();
    batiment.accept(visiteur);
    String expected = "Bâtiment Bâtiment A (1)\n";
    assertEquals(expected, visiteur.getResult());
  }

  @Test
  void testVisiteEtage() {
    Etage etage = new Etage(1);
    Batiment batiment =
        new Batiment(1, "Bâtiment A", "Commercial", new ArrayList<>(), Arrays.asList(etage));
    BureauxVisiteur visiteur = new BureauxVisiteur();
    batiment.accept(visiteur);
    String expected = "Bâtiment Bâtiment A (1)\n    Etage 1:\n";
    assertEquals(expected, visiteur.getResult());
  }

  @Test
  void testVisitePiece() {
    Etage etage = new Etage(1);
    Piece piece = new Piece(20, false, 10, etage);
    Batiment batiment =
        new Batiment(1, "Bâtiment A", "Commercial", Arrays.asList(piece), Arrays.asList(etage));
    BureauxVisiteur visiteur = new BureauxVisiteur();
    batiment.accept(visiteur);
    String expected = "Bâtiment Bâtiment A (1)\n    Etage 1:\n        Pièce 10 - Type: Autre\n";
    assertEquals(expected, visiteur.getResult());
  }

  @Test
  void testVisiteBatimentAvecPlusieursEtagesEtPieces() {
    Etage etage1 = new Etage(1);
    Etage etage2 = new Etage(2);
    Piece piece1 = new Piece(20, false, 101, etage1);
    Piece piece2 = new Piece(30, true, 102, etage1);
    Piece piece3 = new Piece(15, false, 201, etage2);
    Batiment batiment = new Batiment(1, "Bâtiment A", "Commercial",
        Arrays.asList(piece1, piece2, piece3), Arrays.asList(etage1, etage2));
    BureauxVisiteur visiteur = new BureauxVisiteur();
    batiment.accept(visiteur);
    String expected = "Bâtiment Bâtiment A (1)\n" + "    Etage 1:\n"
        + "        Pièce 101 - Type: Autre\n" + "        Pièce 102 - Type: Bureau\n"
        + "    Etage 2:\n" + "        Pièce 201 - Type: Autre\n";
    assertEquals(expected, visiteur.getResult());
  }

  @Test
  void testGetResult() {
    Batiment batiment =
        new Batiment(1, "Bâtiment A", "Commercial", new ArrayList<>(), new ArrayList<>());
    BureauxVisiteur visiteur = new BureauxVisiteur();
    batiment.accept(visiteur);
    assertNotNull(visiteur.getResult());
    assertTrue(visiteur.getResult().contains("Bâtiment"));
  }
}
