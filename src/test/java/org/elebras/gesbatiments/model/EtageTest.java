package org.elebras.gesbatiments.model;

import org.elebras.gesbatiments.visiteur.BatimentVisiteur;
import org.elebras.gesbatiments.visiteur.BureauxVisiteur;
import org.elebras.gesbatiments.visiteur.Visiteur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @Test
  void testEquals() {
    Etage etage1 = new Etage(1);
    Etage etage2 = new Etage(1);
    assertEquals(etage1, etage2);
  }

  @Test
  void testAccept() {
    Visiteur visiteur = new BatimentVisiteur();
    Etage etage = new Etage(1);
    etage.accept(visiteur);
    String result = visiteur.getResult();
    System.out.println(result);
    assertTrue(result.contains("Etage 1"));
  }
}
