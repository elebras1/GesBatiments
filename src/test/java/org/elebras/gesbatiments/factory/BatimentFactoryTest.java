package org.elebras.gesbatiments.factory;

import org.elebras.gesbatiments.model.Batiment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BatimentFactoryTest {
  private BatimentFactory factory;

  @BeforeEach
  public void init() {
    this.factory = new BatimentFactory();
  }

  @Test
  public void valeursInitialTest() {
    assertEquals(this.factory.getUsage(), "Indéfini");
    assertEquals(this.factory.getNombreEtages(), 1);
    assertEquals(this.factory.getNumeroPremierEtage(), 0);
    assertEquals(this.factory.getNumeroPremierePiece(), 0);
  }

  @Test
  public void setterTest() {
    this.factory.setUsage("Education");
    this.factory.setNombreEtages(7);
    this.factory.setNumeroPremierEtage(1);
    this.factory.setNumeroPremierePiece(0);

    assertEquals(this.factory.getUsage(), "Education");
    assertEquals(this.factory.getNombreEtages(), 7);
    assertEquals(this.factory.getNumeroPremierEtage(), 1);
    assertEquals(this.factory.getNumeroPremierePiece(), 0);
  }

  @Test
  public void construireBatimentNbPiecesTropPetitTest() {
    Batiment batimentA = this.factory.construire("A", -1, 1, 19);
    assertNull(batimentA);
  }

  @Test
  public void construireBatimentSurfaceTopPetiteTest() {
    Batiment batimentA = this.factory.construire("A", 8, 1, 5);
    assertNull(batimentA);
  }

  @Test
  public void construireBatimentNomSansCaractereTest() {
    Batiment batimentA = this.factory.construire("", 12, 1, 19);
    assertNull(batimentA);
  }

  @Test
  public void construireBatimentBasiqueTest() {
    Batiment batimentA = this.factory.construire("A", 12, 1, 19);
    assertEquals(batimentA.getPieces().size(), 12);
    assertEquals(batimentA.getPieces().get(0).getSurface(), 19);
  }

  @Test
  public void contruireBatimentTest() {
    this.factory.setUsage("Education");
    this.factory.setNombreEtages(3);
    Batiment batimentA = this.factory.construire("A", 12, 1, 19);
    assertEquals(batimentA.getPieces().size(), 36);
    assertEquals(batimentA.getPieces().get(0).getSurface(), 19);
  }

  @Test
  public void contruirePlusieursBatimentsTest() {
    Integer nombreBatiments = 14;
    List<Batiment> batiments = new ArrayList<>();
    String nomCommun = "Immeuble";


    for (int i = 0; i < nombreBatiments; i++) {
      batiments.add(this.factory.construire(nomCommun + i, 15, 3, 25));
    }

    assertEquals(this.factory.getNombreBatiment(), nombreBatiments);
  }

  @Test
  public void construireBatimentAvecPlusieursEtagesTest() {
    Batiment batimentA = this.factory.construire("A", 12, 3, 19);
    assertEquals(batimentA.getEtages().size(), 1);
  }

}
