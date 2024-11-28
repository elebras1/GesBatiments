package org.elebras.gesbatiments.facade;

import org.elebras.gesbatiments.verificateur.VerificationResultat;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;
import org.elebras.gesbatiments.visiteur.BureauxVisiteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.elebras.gesbatiments.facade.AjouterBatimentResultat.*;
import static org.elebras.gesbatiments.verificateur.VerificationResultat.AUCUNE_ERREUR;
import static org.elebras.gesbatiments.verificateur.VerificationResultat.PIECE_ERREUR;
import static org.junit.Assert.*;

public class CampusTest {
  public Campus campus;

  @BeforeEach
  public void setUp() {
    this.campus = new Campus();
  }

  @Test
  public void testAjouterBatiment() {
    AjouterBatimentResultat resultat = this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    assertEquals(SUCCESS, resultat);
  }

  @Test
  public void testAjouterBatimentDejaExistant() {
    this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    AjouterBatimentResultat resultat = this.campus.ajouterBatiment("Batiment A", 8, 5, 19);
    assertEquals(BATIMENT_DEJA_EXISTANT, resultat);
  }

  @Test
  public void testAjouterBatimentParametresInvalides() {
    AjouterBatimentResultat resultat = this.campus.ajouterBatiment("Batiment B", 0, 10, 11);
    assertEquals(PARAMETRES_INVALIDES, resultat);
    resultat = this.campus.ajouterBatiment("Batiment B", 8, -1, 11);
    assertEquals(PARAMETRES_INVALIDES, resultat);
    resultat = this.campus.ajouterBatiment("Batiment B", 8, 10, 6);
    assertEquals(PARAMETRES_INVALIDES, resultat);
  }

  @Test
  public void testAjouterBatimentsAucuneErreur() {
    File file = new File("batiments.json");
    Map<String, VerificationResultat> resultat = this.campus.ajouterBatiments(file);
    assertEquals(3, resultat.size());
    assertEquals(AUCUNE_ERREUR, resultat.get("A"));
    assertEquals(AUCUNE_ERREUR, resultat.get("B"));
    assertEquals(AUCUNE_ERREUR, resultat.get("C"));
  }

  @Test
  public void testAjouterBatimentsJsonCorrompu() {
    File file = new File("batimentsCorrompu.json");
    Map<String, VerificationResultat> resultat = this.campus.ajouterBatiments(file);
    assertEquals(new HashMap<>(), resultat);
  }

  @Test
  public void testAjouterBatimentsNonOrdonne() {
    File file = new File("batimentsNonOrdonne.json");
    Map<String, VerificationResultat> resultat = this.campus.ajouterBatiments(file);
    assertEquals(3, resultat.size());
    assertEquals(PIECE_ERREUR, resultat.get("A"));
    assertEquals(AUCUNE_ERREUR, resultat.get("B"));
    assertEquals(PIECE_ERREUR, resultat.get("C"));
  }

  @Test
  public void testAjouterBatimentsVide() {
    File file = new File("batimentsVide.json");
    Map<String, VerificationResultat> resultat = this.campus.ajouterBatiments(file);
    assertEquals(new HashMap<>(), resultat);
  }

  @Test
  public void testModifierNom() {
    this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    this.campus.ajouterBatiment("Batiment B", 8, 5, 19);
    this.campus.ajouterBatiment("Batiment C", 8, 10, 11);
    String resultat = this.campus.modifierNomBatiment(2, "Batiment D");
    assertEquals("Batiment D", resultat);
    assertEquals("Batiment D", this.campus.getNomBatiment(2));
  }

  @Test
  public void testModifierNomDejaPris() {
    this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    this.campus.ajouterBatiment("Batiment B", 8, 5, 19);
    this.campus.ajouterBatiment("Batiment C", 8, 10, 11);
    String resultat = this.campus.modifierNomBatiment(3, "Batiment B");
    assertNull(resultat);
    assertEquals("Batiment C", this.campus.getNomBatiment(3));
  }

  @Test
  public void testSupprimeBatiment() {
    this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    this.campus.ajouterBatiment("Batiment B", 8, 5, 19);
    this.campus.ajouterBatiment("Batiment C", 8, 10, 11);
    boolean result = this.campus.supprimeBatiment(2);
    assertTrue(result);
    assertEquals(2, this.campus.afficherBatimentsNom().size());
  }

  @Test
  public void testSupprimeBatimentInexistant() {
    this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    this.campus.ajouterBatiment("Batiment B", 8, 5, 19);
    this.campus.ajouterBatiment("Batiment C", 8, 10, 11);
    boolean result = this.campus.supprimeBatiment(8);
    assertFalse(result);
    assertEquals(3, this.campus.afficherBatimentsNom().size());
  }

  @Test
  public void afficherBatiment1() {
    this.campus.setNombreEtages(1);
    this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    this.campus.ajouterBatiment("Batiment B", 8, 5, 19);
    this.campus.ajouterBatiment("Batiment C", 8, 10, 11);
    BatimentVisiteur visiteur = new BatimentVisiteur();
    String resultat = this.campus.afficherBatiment(visiteur, 2);
    assertTrue(resultat.contains("Batiment B"));
    assertTrue(resultat.contains("- Surface: 19 m²"));
  }

  @Test
  public void afficherBatiment2() {
    this.campus.setNombreEtages(1);
    this.campus.ajouterBatiment("Batiment A", 12, 1, 19);
    this.campus.ajouterBatiment("Batiment B", 8, 5, 19);
    this.campus.ajouterBatiment("Batiment C", 8, 10, 11);
    BureauxVisiteur visiteur = new BureauxVisiteur();
    String resultat = this.campus.afficherBatiment(visiteur, 2);
    assertTrue(resultat.contains("Batiment B"));
    assertTrue(resultat.contains("Pièce 1 - Type: Bureau"));
    assertTrue(resultat.contains("- Type: Autre"));
  }

}
