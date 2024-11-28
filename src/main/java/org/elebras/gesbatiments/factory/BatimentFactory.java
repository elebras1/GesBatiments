package org.elebras.gesbatiments.factory;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * La classe {@link BatimentFactory} est responsable de la création de bâtiments avec des étages et
 * des pièces. Elle permet de configurer divers paramètres du bâtiment, tels que le nombre d'étages,
 * l'usage du bâtiment, et le nombre de pièces par étage.
 */
public class BatimentFactory {

  /**
   * Le nombre de bâtiments créés.
   */
  private Integer nombreBatiment;

  /**
   * Le numéro de la première pièce.
   */
  private Integer numeroPremierePiece;

  /**
   * Le numéro du premier étage.
   */
  private Integer numeroPremierEtage;

  /**
   * Le nombre d'étages du bâtiment.
   */
  private Integer nombreEtages;

  /**
   * L'usage du bâtiment (ex : Logement, Bureau, etc.).
   */
  private String usage;

  /**
   * Constructeur de la classe {@link BatimentFactory}. Initialise les attributs avec des valeurs
   * par défaut.
   */
  public BatimentFactory() {
    this.nombreBatiment = 0;
    this.numeroPremierePiece = 0;
    this.numeroPremierEtage = 0;
    this.nombreEtages = 1;
    this.usage = "Indéfini";
  }

  /**
   * Construit un bâtiment avec un nombre d'étages, de pièces par étage et de bureaux. La méthode
   * génère une liste d'étages et de pièces, en fonction des paramètres fournis et des attributs de
   * l'objet actuel.
   *
   * @param nom Le nom du bâtiment.
   * @param nbPieceParEtage Le nombre de pièces par étage.
   * @param nbBureau Le nombre de bureaux à créer dans le bâtiment.
   * @param surfacePiece La surface de chaque pièce en mètres carrés.
   * @return Un objet {@link Batiment} représentant le bâtiment créé, ou {@code null} si les
   *         paramètres sont invalides.
   */
  public Batiment construire(String nom, Integer nbPieceParEtage, Integer nbBureau,
      Integer surfacePiece) {
    if (nbPieceParEtage < 1 || surfacePiece < 9 || Objects.equals(nom, "") || nbBureau < 0) {
      return null;
    }

    List<Etage> etages = new ArrayList<>();
    List<Piece> pieces = new ArrayList<>();

    int nbBureauCreer = 0;
    boolean estBureau;
    int numeroPiece = this.numeroPremierePiece;
    for (int i = 0; i < this.nombreEtages; i++) {
      Etage etage = new Etage(this.numeroPremierEtage + i);
      etages.add(etage);
      for (int j = 0; j < nbPieceParEtage; j++) {
        estBureau = nbBureauCreer < nbBureau;
        pieces.add(new Piece(surfacePiece, estBureau, numeroPiece++, etage));
        nbBureauCreer++;
      }
    }

    return new Batiment(++this.nombreBatiment, nom, this.usage, pieces, etages);
  }

  /**
   * Définit le numéro de la première pièce à utiliser pour la numérotation des pièces.
   *
   * @param numero Le numéro de la première pièce.
   */
  public void setNumeroPremierePiece(Integer numero) {
    this.numeroPremierePiece = numero;
  }

  /**
   * Définit le numéro du premier étage à utiliser pour la numérotation des étages.
   *
   * @param numero Le numéro du premier étage.
   */
  public void setNumeroPremierEtage(Integer numero) {
    this.numeroPremierEtage = numero;
  }

  /**
   * Définit le nombre d'étages du bâtiment.
   *
   * @param nombre Le nombre d'étages à définir.
   */
  public void setNombreEtages(Integer nombre) {
    this.nombreEtages = nombre;
  }

  /**
   * Définit l'usage du bâtiment.
   *
   * @param usage L'usage du bâtiment à définir (par exemple : "Logement", "Bureau", etc.).
   */
  public void setUsage(String usage) {
    this.usage = usage;
  }

  /**
   * Récupère le numéro de la première pièce.
   *
   * @return Le numéro de la première pièce.
   */
  public Integer getNumeroPremierePiece() {
    return numeroPremierePiece;
  }

  /**
   * Récupère le numéro du premier étage.
   *
   * @return Le numéro du premier étage.
   */
  public Integer getNumeroPremierEtage() {
    return numeroPremierEtage;
  }

  /**
   * Récupère le nombre d'étages du bâtiment.
   *
   * @return Le nombre d'étages du bâtiment.
   */
  public Integer getNombreEtages() {
    return nombreEtages;
  }

  /**
   * Récupère l'usage du bâtiment.
   *
   * @return L'usage du bâtiment.
   */
  public String getUsage() {
    return usage;
  }

  /**
   * Récupère le nombre de bâtiments créés.
   *
   * @return Le nombre de bâtiments créés.
   */
  public Integer getNombreBatiment() {
    return nombreBatiment;
  }

  /**
   * Incrémente le nombre de bâtiments créés.
   */
  public void incrementNombreBatiment() {
    this.nombreBatiment++;
  }
}
